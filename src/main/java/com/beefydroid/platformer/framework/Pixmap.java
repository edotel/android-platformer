package com.beefydroid.platformer.framework;

import com.beefydroid.platformer.framework.Graphics.PixmapFormat;

/**
 * Created by Leo on 7/06/13.
 */
public interface Pixmap {

    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
