package com.beefydroid.platformer.framework.math;

/**
 * Created by Leo on 7/06/13.
 */
public class Sphere {
    public final Vector3 center = new Vector3();
    public float radius;
    public Sphere(float x, float y, float z, float radius){
        this.center.set(x, y, z);
        this.radius = radius;
    }
}
