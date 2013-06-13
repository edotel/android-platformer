package com.beefydroid.simpleplatformer.framework.math;

/**
 * Created by Leo on 7/06/13.
 */
public class Rectangle {public final Vector2 lowerLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height){
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }
}
