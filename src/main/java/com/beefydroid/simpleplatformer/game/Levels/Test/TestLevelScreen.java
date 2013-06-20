package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.Game;
import com.beefydroid.simpleplatformer.framework.gl.Camera2D;
import com.beefydroid.simpleplatformer.framework.gl.SpriteBatcher;
import com.beefydroid.simpleplatformer.framework.impl.GLScreen;
import com.beefydroid.simpleplatformer.framework.math.Vector2;
import com.beefydroid.simpleplatformer.game.levels.test.TestWorld.TestWorldListener;

import javax.microedition.khronos.opengles.GL10;

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
        // event listener. Play sounds and stuff.
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
        world = new TestWorld(listener);

        float game_height = 10.0f;
        float game_width = glGraphics.getWidth() / glGraphics.getHeight() * 15.0f;
        renderer = new TestWorldRenderer(glGraphics, batcher, game_width, game_height, world);

    }
    @Override
    public void update(float deltaTime){

        //Update game here

        //Get input from touch screen or accelerometer and pass through to world
        //eg world.update(dt, keys)
        //Late get keys from touch screen
        int keys = 0;
        world.update(deltaTime, keys);
        world.jumpMan.moveRight();

    }
    @Override
    public void present(float deltaTime){
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);

        renderer.render();

        //Render GUI and menu stuff here

        gl.glDisable(GL10.GL_BLEND);

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
