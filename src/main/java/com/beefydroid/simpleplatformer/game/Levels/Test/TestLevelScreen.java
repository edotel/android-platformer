package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.Game;
import com.beefydroid.simpleplatformer.framework.gl.Camera2D;
import com.beefydroid.simpleplatformer.framework.gl.SpriteBatcher;
import com.beefydroid.simpleplatformer.framework.impl.GLScreen;
import com.beefydroid.simpleplatformer.framework.math.Vector2;
import com.beefydroid.simpleplatformer.game.levels.test.TestWorld.TestWorldListener;

/**
 * Created by Leo on 12/06/13.
 */
public class TestLevelScreen extends GLScreen{
    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    TestWorldListener listener;
    TestWorld world;
    TestWorldRenderer renderer;


    public TestLevelScreen(Game game){
        super (game);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        listener = new TestWorldListener() {
            @Override
            public void jump() {

            }

            @Override
            public void highJump() {

            }

            @Override
            public void hit() {

            }

            @Override
            public void coin() {

            }
        };

        guiCam = new Camera2D(glGraphics, glGraphics.getWidth(), glGraphics.getHeight());

    }
    @Override
    public void update(float deltaTime){

    }
    @Override
    public void present(float deltaTime){

    }
    @Override
    public void pause(){

    }
    @Override
    public void resume(){

    }
    @Override
    public void dispose(){

    }

}
