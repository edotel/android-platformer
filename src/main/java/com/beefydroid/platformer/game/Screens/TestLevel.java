package com.beefydroid.platformer.game.Screens;

import android.view.Display;

import com.beefydroid.platformer.framework.Game;
import com.beefydroid.platformer.framework.gl.Camera2D;
import com.beefydroid.platformer.framework.gl.SpriteBatcher;
import com.beefydroid.platformer.framework.impl.GLScreen;
import com.beefydroid.platformer.framework.math.Vector2;

/**
 * Created by Leo on 12/06/13.
 */
public class TestLevel extends GLScreen{
    Camera2D cam;
    SpriteBatcher batcher;
    Vector2 touchPoint;

    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;

    public TestLevel(Game game){
        super (game);
        Display display = getWindowManager()
        cam = Camera2D(glGraphics, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

}
