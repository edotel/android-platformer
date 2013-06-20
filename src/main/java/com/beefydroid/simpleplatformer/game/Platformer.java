package com.beefydroid.simpleplatformer.game;

/**
 * Created by Leo on 12/06/13.
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.beefydroid.simpleplatformer.framework.Screen;
import com.beefydroid.simpleplatformer.framework.impl.GLGame;
import com.beefydroid.simpleplatformer.game.levels.test.TestLevelScreen;

public class Platformer extends GLGame{
    boolean firstTimeCreated = true;

    public Screen getStartScreen() {
        return new TestLevelScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreated) {
            //Load settings
            Settings.load(getFileIO());
            //Load assets
            Assets.load(this);

            firstTimeCreated = false;
        } else {
            Assets.reload();
            //Reload assets in case some were freed from RAM
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        //If sound enabled - pause music
    }
}
