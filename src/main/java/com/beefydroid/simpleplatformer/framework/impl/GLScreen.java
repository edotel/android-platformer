package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 12/06/13.
 */

import com.beefydroid.simpleplatformer.framework.Game;
import com.beefydroid.simpleplatformer.framework.Screen;

public abstract class GLScreen extends Screen{
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game){
        super (game);
        glGame = (GLGame)game;
        glGraphics = glGame.getGLGraphics();
    }
}
