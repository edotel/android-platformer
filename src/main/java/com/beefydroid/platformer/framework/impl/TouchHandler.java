/**
 * Created by Leo on 10/06/13.
 *
 * Interface for different kinds of touch listeners (eg Single touch, multi-touch)
 *
 */
package com.beefydroid.platformer.framework.impl;

import java.util.List;

import android.view.View.OnTouchListener;

import com.beefydroid.platformer.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List <TouchEvent> getTouchEvents();
}
