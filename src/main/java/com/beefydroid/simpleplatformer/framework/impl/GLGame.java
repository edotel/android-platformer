package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 12/06/13.
 *
 * Base OpenGL Game class that contains the relevant components for making a game. Extends Android
 * Activity that calls onCreate, onPause, onResume.
 *
 * Implements the interface classes of Game (getInput, getFileIO, getGraphics, getAudio, setScreen,
 * getCurrentScreen, setStartScreen
 *
 * Implements GLSurfaceView Renderer (onSurfaceChanged, onSurfaceCreated, onDrawFrame)
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
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
import com.beefydroid.simpleplatformer.framework.math.Vector2;

public class GLGame extends Activity implements Game, Renderer{
    enum GLGameState {
        Initialized, Running, Paused, Finished, Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    long startTime = System.nanoTime();
    WakeLock wakeLock;

    int screen_width, screen_height;
    //float aspectRatio;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);

        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "GLGame");

        // Get screen dimensions and density
        screen_width = getWindowManager().getDefaultDisplay().getWidth();
        screen_height = getWindowManager().getDefaultDisplay().getHeight();
    }

    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLGameState state = null;

        //Synchronized is used so we can access OpenGL ES, which is only in the rendering
        //Thread!
        synchronized (stateChanged) {
            state = this.state;
        }
        if (state == GLGameState.Running) {
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            screen.update(deltaTime);
            screen.present(deltaTime);
        } else if (state == GLGameState.Paused) {
            screen.pause();
            synchronized (stateChanged) {
                this.state = GLGameState.Idle;
                // Notify the UI thread that it can now pause
                // Important to make sure the rendering thread is paused/shut
                // down in case Activity is paused
                stateChanged.notifyAll();
            }
        } else if (state == GLGameState.Finished) {
            screen.pause();
            screen.dispose();
            synchronized (stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }

    }

    @Override
    public void onPause() {
        synchronized (stateChanged) {
            if (isFinishing()) {
                state = GLGameState.Finished;
            } else {
                state = GLGameState.Paused;
            }
            while (true) {
                try {
                    stateChanged.wait();
                    break;
                } catch (InterruptedException e){
                }
            }
        }
        wakeLock.release();
        glView.onPause();
        super.onPause();
    }

    @Override
    public void onSurfaceChanged(GL10 arg0, int arg1, int arg2) {
        // I'm just s stub :(
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glGraphics.setGL(gl);

        synchronized (stateChanged) {
            if (state == GLGameState.Initialized) {
                screen = getStartScreen();
            }
            state = GLGameState.Running;
            screen.resume();
            startTime = System.nanoTime();
        }
    }

    public GLGraphics getGLGraphics() {
        return glGraphics;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        throw new IllegalStateException("We're using the superior OpenGL!");
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen newScreen) {
        if(screen == null){
            throw new IllegalArgumentException("Screen must not be null!");
        }
        this.screen.pause();
        this.screen.dispose();
        newScreen.resume();
        newScreen.update(0);
        this.screen = newScreen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }
}