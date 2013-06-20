package com.beefydroid.simpleplatformer.game.levels.test;

import android.util.Log;

import com.beefydroid.simpleplatformer.framework.gl.Animation;
import com.beefydroid.simpleplatformer.framework.gl.Camera2D;
import com.beefydroid.simpleplatformer.framework.gl.SpriteBatcher;
import com.beefydroid.simpleplatformer.framework.gl.TextureRegion;
import com.beefydroid.simpleplatformer.framework.impl.GLGraphics;
import com.beefydroid.simpleplatformer.game.Assets;
import com.beefydroid.simpleplatformer.game.objects.JumpMan;
import com.beefydroid.simpleplatformer.game.objects.Platform;

import javax.microedition.khronos.opengles.GL10;

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
        // Keep game cam tied to JumpMan
        gameCam.position.x = world.jumpMan.position.x + 1;
        gameCam.position.y = world.jumpMan.position.y + 2;
        gameCam.setViewportAndMatrices();

        renderBackground();
        renderObjects();
    }

    public void renderBackground() {
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(gameCam.position.x, gameCam.position.y, frustum_width,
                frustum_height, Assets.backgroundRegion);
        batcher.endBatch();
    }

    public void renderObjects(){
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //Pile objects here
        batcher.beginBatch(Assets.items);
        renderJumpMan();
        renderPlatforms();

        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);

    }

    public void renderJumpMan(){
        TextureRegion keyFrame;
        switch(world.jumpMan.getState()){
            case JumpMan.STATE_LEFT:
            case JumpMan.STATE_RIGHT:
                keyFrame = Assets.jumpManFall.getKeyFrame(world.jumpMan.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case JumpMan.STATE_JUMPING:
                keyFrame = Assets.jumpManJump.getKeyFrame(world.jumpMan.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case JumpMan.STATE_IDLE:
            default:
                keyFrame = Assets.jumpManHit;
                break;
        }
        float side = world.jumpMan.velocity.x < 0 ? -1: 1;
        batcher.drawSprite(world.jumpMan.position.x, world.jumpMan.position.y, side * 1, 1, keyFrame);
    }

    public void renderPlatforms(){
        float platWidth = Platform.PLATFORM_WIDTH;
        float platHeight = Platform.PLATFORM_HEIGHT;
        TextureRegion platformRegion = Assets.platform;
        //float platX;
        //float platY;

        for(Platform p : world.platforms){
            // Draw blocks f platforms

            batcher.drawSprite(p.position.x, p.position.y, platWidth ,platHeight, platformRegion);
            //Log.d("World", "drawing platform at x: " + platX + ", y: " + platY +
                   // ", width: " + platWidth + ", height: " + platHeight);
        }
    }

}
