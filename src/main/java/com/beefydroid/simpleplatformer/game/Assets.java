package com.beefydroid.simpleplatformer.game;

import android.util.DisplayMetrics;

import com.beefydroid.simpleplatformer.framework.gl.Animation;
import com.beefydroid.simpleplatformer.framework.gl.Texture;
import com.beefydroid.simpleplatformer.framework.gl.TextureRegion;
import com.beefydroid.simpleplatformer.framework.impl.GLGame;
import com.beefydroid.simpleplatformer.framework.Sound;

/**
 * Created by Leo on 13/06/13.
 *
 * Assets class. Probably a better way of handling assets, but this will do for now.
 */
public class Assets {
    //Background
    public static Texture background;
    public static TextureRegion backgroundRegion;

    //Game items
    public static Texture items;

    public static Animation jumpManJump;
    public static Animation jumpManFall;
    public static TextureRegion jumpManHit;

    public static Animation playerMove;
    public static Texture playerSprite;
    public static TextureRegion playerIdle;

    public static TextureRegion platform;

    private static DisplayMetrics metrics;

    private static String backgroundImageLoc;
    private static String playerImageLoc;
    private static String aspectRatioString;
    private static String dpiString;

    public static float aspectRatio;
    public static int backgroundHeight;
    public static int backgroundWidth;
    public static int playerFrameSize;


    public static void load(GLGame game){

        // Get screen information (density, aspect ratio)
        metrics = game.getResources().getDisplayMetrics();
        aspectRatio = game.getGLGraphics().getAspectRadio();

        if (aspectRatio > 1.68) {
            // 16x9 (ish)
            aspectRatioString = "16-9";
        } else {
            aspectRatioString = "16-10";
        }

        /* Note:
       To differentiate between 16-9 and 16-10:
       16:9 = 1.777
       16:10 = 1.6
       midpoint = 1.68
       if > mid -> 16:9
       else if < mid -> 16:10
       */

        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                dpiString = "ldpi";
                backgroundHeight = 0;
                playerFrameSize = 0;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                dpiString = "mdpi";
                backgroundHeight = aspectRatio > 1.68? 270 : 320;
                backgroundWidth = aspectRatio > 1.68 ? 1080 : 1280;
                playerFrameSize = 64;
                break;
            case DisplayMetrics.DENSITY_TV:
                dpiString = "tvdpi";
                backgroundHeight = aspectRatio > 1.68? 450 : 480;
                backgroundWidth = aspectRatio > 1.68 ? 1800: 1920;
                playerFrameSize = 85;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                dpiString = "hdpi";
                backgroundHeight = aspectRatio > 1.68? 450 : 480;
                backgroundWidth = aspectRatio > 1.68 ? 1800: 1920;
                playerFrameSize = 96;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                dpiString = "xhdpi";
                backgroundHeight = aspectRatio > 1.68? 720 : 800;
                backgroundWidth = aspectRatio > 1.68 ? 2880: 3200;
                playerFrameSize = 128;
                break;
        }
        backgroundImageLoc = dpiString + "/" + aspectRatioString + "/cloudymoon-" + dpiString + "-" +
                            aspectRatioString + ".jpg";
        background = new Texture(game, backgroundImageLoc);
        backgroundRegion = new TextureRegion(background, 0, 0, backgroundWidth, backgroundHeight);

        // Badlogic's items (temp)
        items = new Texture(game, "items.png");

        playerImageLoc = dpiString + "/vesper-tileset-" + dpiString + "-marked.png";

        playerSprite = new Texture(game, playerImageLoc);


        playerMove = new Animation(0.2f,
                new TextureRegion(playerSprite, 0, 0, playerFrameSize, playerFrameSize),
                new TextureRegion(playerSprite, playerFrameSize, 0, playerFrameSize, playerFrameSize),
                new TextureRegion(playerSprite, playerFrameSize * 2, 0, playerFrameSize, playerFrameSize),
                new TextureRegion(playerSprite, playerFrameSize * 3, 0, playerFrameSize, playerFrameSize),
                new TextureRegion(playerSprite, playerFrameSize * 4, 0, playerFrameSize, playerFrameSize)
        );
        playerIdle = new TextureRegion(playerSprite, playerFrameSize, 3 * playerFrameSize,
                                        playerFrameSize, playerFrameSize);


        // Player from Badlogic (temporary)
        jumpManJump = new Animation(0.5f,
                new TextureRegion(items, 0, 128, 32, 32),
                new TextureRegion(items, 32, 128, 32, 32));
        jumpManFall = new Animation(0.2f,
                new TextureRegion(items, 64, 128, 32, 32),
                new TextureRegion(items, 96, 128, 32, 32));
        jumpManHit = new TextureRegion(items, 128, 128, 32, 32);

        platform = new TextureRegion(items, 64, 160, 64, 16);
    }
    public static void reload() {
        background.reload();
        items.reload();
        // TODO: enable sound if needed
    }

    public static void playSound(Sound sound){
        if(Settings.soundEnabled){
            sound.play(1);
        }
    }
}
