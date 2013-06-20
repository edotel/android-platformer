package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.math.OverlapTester;
import com.beefydroid.simpleplatformer.framework.math.Vector2;
import com.beefydroid.simpleplatformer.game.objects.JumpMan;
import com.beefydroid.simpleplatformer.game.objects.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 12/06/13.
 */
public class TestWorld {
    public interface TestWorldListener{
        public void jump();

        public void highJump();

        public void hit();

        public void coin();
    }

    public static final float WORLD_WIDTH = 40;
    public static final float WORLD_HEIGHT = 10;

    public static final int STATE_RUNNING = 0;
    public static final int STATE_NEXT_LEVEL = 1;
    public static final int STATE_GAME_OVER = 2;
    public static final Vector2 gravity = new Vector2(0, -2.0f);

    public final JumpMan jumpMan;
    public final List<Platform> platforms;

    public final TestWorldListener listener;

    public int state;

    public TestWorld(TestWorldListener listener){
        this.jumpMan = new JumpMan(5,7);
        this.platforms = new ArrayList <Platform>();
        this.listener = listener;

        setupLevel();
    }

    public void setupLevel() {
        float startX = 5;
        float startY = 5;
        for(int x = 0; x < 5 * Platform.PLATFORM_WIDTH; x += Platform.PLATFORM_WIDTH) {
            Platform p = new Platform(startX + x, startY);
            platforms.add(p);
        }
        //Platform firstPlatform = new Platform(5, 5);

    }

    public void update(float deltaTime, int keysPressed){
        updateJumpMan(deltaTime, keysPressed);
        updatePlatforms(deltaTime);
    }

    public void updateJumpMan(float deltaTime, int keys){
        jumpMan.update(deltaTime);
        //Check each platform if the jumpMan has hit it
        for(Platform p : platforms){
            /*
            if(OverlapTester.checkRectanglesSideHit(jumpMan.bounds, p.bounds)
                    == OverlapTester.RectangleSideHit.Top) {
                //If so, set his velocity to zero (I hope this works..)
                jumpMan.setGrounded(true);
            }*/
            if(OverlapTester.overlapRectangles(jumpMan.bounds, p.bounds)){
                jumpMan.setGrounded(true);
            }
        }
    }

    public void updatePlatforms(float deltaTime){
        for(Platform p : platforms) {
            p.update(deltaTime);
        }
    }

}
