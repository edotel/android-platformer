package com.beefydroid.simpleplatformer.game.objects;

import android.util.Log;

import com.beefydroid.simpleplatformer.framework.math.Rectangle;
import com.beefydroid.simpleplatformer.framework.DynamicGameObject;

/**
 * Created by Leo on 13/06/13.
 */
public class Platform extends DynamicGameObject{
    public static final int TYPE_STATIC = 0;
    public static final int TYPE_MOVING = 1;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_PULVERISING = 1;
    public static final float PULVERISE_TIME = 0.2f * 4;
    public static final float VELOCITY = 2;

    // Hard coded dimensions, for now
    // TODO: get sprite dimensions from file
    public static final float PLATFORM_WIDTH = 2;
    public static final float PLATFORM_HEIGHT = 0.5f;

    int type;
    int state;
    float range;
    float stateTime;
    int repeat_x;
    int repeat_y;

    public Platform(float x, float y){
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        //Log.d("Platform", "width: " + bounds.width + ", height: " + bounds.height);
        this.type = TYPE_STATIC;
        this.stateTime = 0;
    }
    /* For moving platform
    public Platform(float x, float y, int repeat_x, int repeat_y, float range){
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.repeat_x = repeat_x;
        this.repeat_y = repeat_y;
        this.type = TYPE_MOVING;
        this.stateTime = 0;
        this.range = range;
        velocity.x = VELOCITY;
    } */

    public void update(float deltaTime) {
        if(type == TYPE_MOVING) {
            position.add(velocity.x * deltaTime, 0);
            bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

            // Move left and right between the start position and the end range
            if(position.x < bounds.width / 2){
                velocity.x = -velocity.x;
                position.x = bounds.width / 2;
            }
            if(position.x > range - bounds.height / 2) {
                velocity.x = -velocity.x;
                position.x = range - bounds.height / 2;
            }
        }
        stateTime += deltaTime;
    }
    public void pulverize() {
        state = STATE_PULVERISING;
        stateTime = 0;
        velocity.x = 0;
    }
}
