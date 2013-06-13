/**
 * Created by Leo on 10/06/13.
 * A generic GameObject that has a Vector2 velocity and acceleration, accel.
 *
 */
package com.beefydroid.simpleplatformer.framework;

import com.beefydroid.simpleplatformer.framework.math.Vector2;

public class DynamicGameObject extends GameObject{
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
