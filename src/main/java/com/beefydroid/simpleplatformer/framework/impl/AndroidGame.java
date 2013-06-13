package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * The initial class that is called, that calls the integrated Android life-cycle onCreate,
 * onResume, and onPause. Also implements basic Game functionality such as getInput, getFileIO,
 * getGraphics, getAudio, setScreen, getCurrentScreen, and launches the initial startScreen with
 * setStartScreen.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.beefydroid.simpleplatformer.framework.Audio;
import com.beefydroid.simpleplatformer.framework.FileIO;
import com.beefydroid.simpleplatformer.framework.Game;
import com.beefydroid.simpleplatformer.framework.Graphics;
import com.beefydroid.simpleplatformer.framework.Input;
import com.beefydroid.simpleplatformer.framework.Screen;
public abstract class AndroidGame extends Activity implements Game{
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;

    private final int SCREEN_WIDTH = 320;
    private final int SCREEN_HEIGHT = 480;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Force fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Make screen dimensions landscape or portrait, depending on what is defined in AndroidManifest.xml
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? SCREEN_HEIGHT : SCREEN_WIDTH;
        int frameBufferHeight = isLandscape ? SCREEN_WIDTH : SCREEN_HEIGHT;

        //Create framebuffer for drawing to screen
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);

        //Point screenSize = new Point();
        //if()
        float scaleX = (float ) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float ) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }

    @Override
    public void onResume(){
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if(isFinishing()){
            screen.dispose();
        }
    }
    @Override
    public Input getInput() {
        return input;
    }
    @Override
    public FileIO getFileIO(){
        return fileIO;
    }
    @Override
    public Graphics getGraphics(){
        return graphics;
    }
    @Override
    public Audio getAudio(){
        return audio;
    }
    @Override
    public void setScreen(Screen screen){
        if(screen == null){
            throw new IllegalArgumentException("Screen must not be null");
        }
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }
    public Screen getCurrentScreen(){
        return screen;
    }
    public Screen getStartScreen(){
        return null;
    }


}
