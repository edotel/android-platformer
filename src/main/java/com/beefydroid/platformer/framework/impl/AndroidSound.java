package com.beefydroid.platformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * Sound implementation for playing back reasonably short sound effects (not so much Music)
 *
 * It can just play a sound - not pausing, stopping. Volume control is supported.
 */

import android.media.SoundPool;

import com.beefydroid.platformer.framework.Sound;
public class AndroidSound implements Sound{
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId){
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);

    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);

    }
}
