package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.gl.Animation;
import com.beefydroid.simpleplatformer.framework.gl.Camera2D;
import com.beefydroid.simpleplatformer.framework.gl.SpriteBatcher;
import com.beefydroid.simpleplatformer.framework.gl.TextureRegion;
import com.beefydroid.simpleplatformer.framework.impl.GLGraphics;
import com.beefydroid.simpleplatformer.game.Assets;
import com.beefydroid.simpleplatformer.game.objects.Player;
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
        //gameCam.zoom = 2.0f;

    }

    public void render(){
        // Keep game cam tied to Player
        gameCam.position.x = world.jumpMan.position.x + 1;
        gameCam.position.y = world.jumpMan.position.y + 1;
        if(gameCam.position.x - (frustum_width / 2) <= 0){
            gameCam.position.x = frustum_width / 2;
        }
        else if(gameCam.position.x > world.worldBounds.width - (frustum_width / 2)){
            gameCam.position.x = world.worldBounds.width - (frustum_width / 2);
        }
        if(gameCam.position.y < (frustum_height / 2) ) {
            gameCam.position.y = frustum_height / 2;
        } else if (gameCam.position.y > world.worldBounds.height - (frustum_height / 2)) {
            gameCam.position.y = world.worldBounds.height - (frustum_height / 2);
        }

        gameCam.setViewportAndMatrices();

        renderBackground();
        renderObjects();
    }

    public void renderBackground() {
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(world.background.position.x, world.background.position.y,
                world.background.bounds.width, world.background.bounds.height,
                Assets.backgroundRegion);
        batcher.endBatch();
    }

    public void renderObjects(){
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //Pile objects here
        batcher.beginBatch(Assets.playerSprite);
        renderJumpMan();
        batcher.endBatch();
        batcher.beginBatch(Assets.items);
        renderPlatforms();
        batcher.endBatch();

        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);

    }

    public void renderJumpMan(){
        TextureRegion keyFrame;
        switch(world.jumpMan.getState()){
            case Player.STATE_LEFT:
            case Player.STATE_RIGHT:
                keyFrame = Assets.playerMove.getKeyFrame(world.jumpMan.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Player.STATE_JUMPING:
                keyFrame = Assets.jumpManJump.getKeyFrame(world.jumpMan.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Player.STATE_IDLE:
            default:
                keyFrame = Assets.playerIdle;
                break;
        }
        float size = world.jumpMan.bounds.width;
        float side = world.jumpMan.velocity.x < 0 ? -1: 1;
        batcher.drawSprite(world.jumpMan.position.x, world.jumpMan.position.y, side * size, size, keyFrame);
    }

    public void renderPlatforms(){
        float platWidth = Platform.PLATFORM_WIDTH;
        float platHeight = Platform.PLATFORM_HEIGHT;
        TextureRegion platformRegion = Assets.platform;

        for(Platform p : world.platforms){
            batcher.drawSprite(p.position.x, p.position.y, platWidth ,platHeight, platformRegion);
        }
    }

}
