package com.beefydroid.platformer.framework.impl;

/**
 * Created by Leo on 12/06/13.
 */

import com.beefydroid.platformer.framework.Game;
import com.beefydroid.platformer.framework.Screen;

public abstract class GLScreen extends Screen{
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game){
        super (game);
        glGame = (GLGame)game;
        glGraphics = glGame.getGLGraphics();
    }
}
