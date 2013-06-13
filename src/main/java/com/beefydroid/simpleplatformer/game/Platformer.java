package com.beefydroid.simpleplatformer.game;

/**
 * Created by Leo on 12/06/13.
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.beefydroid.simpleplatformer.framework.Screen;
import com.beefydroid.simpleplatformer.framework.impl.GLGame;

public class Platformer extends GLGame{
    boolean firstTimeCreated = false;

    public Screen getStartScreen() {
        //return new FirstLevelTest(this);
        return null;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreated) {
            //Load settings

            //Load assets

            firstTimeCreated = false;
        } else {
            //Reload assets in case some were freed from RAM
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        //If sound enabled - pause music
    }
}
