package com.beefydroid.simpleplatformer.game;

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

    public static TextureRegion platform;


    public static void load(GLGame game){
        background = new Texture(game, "background.jpg");
        backgroundRegion = new TextureRegion(background, 0, 0, 950, 650);

        // Badlogic's items (temp)
        items = new Texture(game, "items.png");

        // JumpMan from Badlogic (temporary)
        jumpManJump = new Animation(0.2f,
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
