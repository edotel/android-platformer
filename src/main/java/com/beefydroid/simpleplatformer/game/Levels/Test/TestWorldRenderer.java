package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.gl.Camera2D;
import com.beefydroid.simpleplatformer.framework.gl.SpriteBatcher;
import com.beefydroid.simpleplatformer.framework.impl.GLGraphics;

/**
 * Created by Leo on 13/06/13.
 */
public class TestWorldRenderer {
    float frustum_width;
    float frustum_height;
    GLGraphics glGraphics;
    TestWorld world;
    Camera2D gameCam;
    SpriteBatcher batcher;

    public TestWorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, float frustum_width, float frustum_height, TestWorld world) {
        this.glGraphics = glGraphics;
        this.batcher = batcher;
        this.frustum_width = frustum_width;
        this.frustum_height = frustum_height;
        this.world = world;
        this.gameCam = new Camera2D(glGraphics, frustum_width, frustum_height);
    }

    public void render(){
        gameCam.position.x = world.jumpMan.position.x + 1;
        gameCam.setViewportAndMatrices();
    }
}
