package com.beefydroid.platformer.framework.math;

/**
 * Created by Leo on 7/06/13.
 */
public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;
    public Circle(float x, float y, float radius){
        this.center.set(x,y);
        this.radius = radius;
    }
}
