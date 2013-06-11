package com.beefydroid.platformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 * A class that holds a bitmap image, with bitmap format.
 */
import android.graphics.Bitmap;
import com.beefydroid.platformer.framework.Graphics.PixmapFormat;
import com.beefydroid.platformer.framework.Pixmap;
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }
    public int getWidth() {
        return bitmap.getWidth();
    }
    public int getHeight() {
        return bitmap.getHeight();
    }
    public PixmapFormat getFormat() {
        return format;
    }
    public void dispose() {
        bitmap.recycle();
    }
}
