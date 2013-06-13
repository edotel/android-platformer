package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * Implementation of Audio. Creates new music (stream from 'disk') and sound effects (load into RAM,
 * play from RAM)
 *
 * No need to release the SoundPool, as Game holds an instance of SoundPool and the SoundPool will
 * be alive as long as Game is alive.
 */

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.beefydroid.simpleplatformer.framework.Audio;
import com.beefydroid.simpleplatformer.framework.Music;
import com.beefydroid.simpleplatformer.framework.Sound;
public class AndroidAudio implements Audio{
    AssetManager assets;
    SoundPool soundPool;

    // Constructor takes in Activity to control volume and to get an AssetManager instance (kinda
    // useful)
    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        // Create sound pool with 20 simultaneous sounds
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e){
            throw new RuntimeException("Couldn't load music '"+filename+"'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '"+filename+"'");
        }
    }
}
