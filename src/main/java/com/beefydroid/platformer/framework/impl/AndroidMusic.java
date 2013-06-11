package com.beefydroid.platformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * Implementation of Music. Plays back music.
 *
 * Can be controlled using play, stop, pause, setLooping, setVolume, and state checking methods:
 * isPlaying, isStopped, isLooping. dispose can be called to release the player.
 */

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.beefydroid.platformer.framework.Music;
public class AndroidMusic implements Music, OnCompletionListener{
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    // Constructor - create and prepare Android MediaPlayer using AssetFileDescriptor parameter
    public AndroidMusic(AssetFileDescriptor assetDescriptor){
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),assetDescriptor.getStartOffset(),assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch(IOException e){
            throw new RuntimeException("Clouldn't load music");
        }
    }

    @Override
    public void play() {
        if(mediaPlayer.isPlaying()){
            return;
        }
        try{
            synchronized(this){
                if(!isPrepared){
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
            }
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized(this){
            isPrepared = false;
        }

    }

    @Override
    public void pause() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);

    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);

    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();

    }

    // set isPrepared to false as playback has finished. Other methods in this class would
    // otherwise have problems.
    public void onCompletion(MediaPlayer player){
        synchronized(this){
            isPrepared = false;
        }
    }
}
