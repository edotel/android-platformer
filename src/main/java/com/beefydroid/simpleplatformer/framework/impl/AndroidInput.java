package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * Input manager for all kinds of input relevant to games.
 * Accelerometer, Keyboard and touch.
 */

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.beefydroid.simpleplatformer.framework.Input;

public class AndroidInput implements Input{
    AccelerometerHandler accelHandler;
    KeyboardHandler keyHandler;
    TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY){
        accelHandler = new AccelerometerHandler(context);
        keyHandler = new KeyboardHandler(view);

        // Check running Android version - if older than API 5, use single-touch, otherwise, use
        // multi-touch
        if(Integer.parseInt(VERSION.SDK) < 5){
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        } else {
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        }
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return keyHandler.getKeyEvents();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
