package com.beefydroid.simpleplatformer.game.levels.test;

import com.beefydroid.simpleplatformer.framework.math.OverlapTester;
import com.beefydroid.simpleplatformer.framework.math.Rectangle;
import com.beefydroid.simpleplatformer.framework.math.Vector2;
import com.beefydroid.simpleplatformer.game.Assets;
import com.beefydroid.simpleplatformer.game.objects.Background;
import com.beefydroid.simpleplatformer.game.objects.Player;
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
    public static final Vector2 gravity = new Vector2(0, -2.0f);
    public static final Rectangle worldBounds = new Rectangle(0, 0, 25, 10);

    public final Player jumpMan;
    public final List<Platform> platforms;
    public final Background background;

    public final Vector2 jumpManStartingPos = new Vector2(0.5f, 7.5f);

    public final TestWorldListener listener;

    public int state;

    public TestWorld(TestWorldListener listener){
        this.jumpMan = new Player(jumpManStartingPos.x, jumpManStartingPos.y, 3.5f, 3.5f);
        this.platforms = new ArrayList <Platform>();
        float backgroundWidth = worldBounds.height * (Assets.aspectRatio > 1.68 ? 1.777f : 1.6f) * 2.5f;
        this.background = new Background(worldBounds.width / 2, worldBounds.height / 2,
                                            backgroundWidth, worldBounds.height);
        this.listener = listener;

        setupLevel();
    }

    public void setupLevel() {
        float startX = 0;
        float startY = 5;
        for(int x = 0; x < 5 * Platform.PLATFORM_WIDTH; x += Platform.PLATFORM_WIDTH) {
            Platform p = new Platform(startX + x, startY);
            platforms.add(p);
        }
        startX = 6.0f * Platform.PLATFORM_WIDTH;
        startY = 4;
        for(int x = 0; x < 4 * Platform.PLATFORM_WIDTH; x += Platform.PLATFORM_WIDTH) {
            Platform p = new Platform(startX + x, startY);
            platforms.add(p);
        }
    }

    public void update(float deltaTime, int keysPressed){
        updateJumpMan(deltaTime, keysPressed);
        updatePlatforms(deltaTime);

    }

    public void updateJumpMan(float deltaTime, int keys){
        jumpMan.update(deltaTime);
        //Check each platform if the jumpMan has hit it
        for(Platform p : platforms){
            if(OverlapTester.overlapRectangles(jumpMan.bounds, p.bounds)){
                jumpMan.setGrounded(true);
            }
            if(checkFallenOutOfBounds()){
                jumpMan.position.set(jumpManStartingPos);
            }
        }
    }
    public boolean checkFallenOutOfBounds(){
        if(jumpMan.position.x > worldBounds.width)
        {
            return true;
        }
        if(jumpMan.position.x < 0){
            return true;
        }
        if (jumpMan.position.y < worldBounds.lowerLeft.y)
        {
            return true;
        }
        if( jumpMan.position.y < 0){
            return true;
        }
        return false;
    }

    public void updatePlatforms(float deltaTime){
        for(Platform p : platforms) {
            p.update(deltaTime);
        }
    }

}
