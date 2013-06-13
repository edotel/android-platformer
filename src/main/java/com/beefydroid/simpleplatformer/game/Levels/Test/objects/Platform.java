package com.beefydroid.simpleplatformer.game.levels.test.objects;

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

    int type;
    int state;
    float range;
    float stateTime;
    float width;
    float height;

    public Platform(float x, float y, float width, float height){
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.type = TYPE_STATIC;
        this.stateTime = 0;
    }
    public Platform(float x, float y, float width, float height, float range){
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.type = TYPE_MOVING;
        this.stateTime = 0;
        this.range = range;
        velocity.x = VELOCITY;
    }

    public void update(float deltaTime) {
        if(type == TYPE_MOVING) {
            position.add(velocity.x * deltaTime, 0);
            bounds.lowerLeft.set(position).sub(width / 2, height / 2);

            // Move left and right between the start position and the end range
            if(position.x < width / 2){
                velocity.x = -velocity.x;
                position.x = width / 2;
            }
            if(position.x > range - height / 2) {
                velocity.x = -velocity.x;
                position.x = range - height / 2;
            }
        }
    }
    public void pulverize() {
        state = STATE_PULVERISING;
        stateTime = 0;
        velocity.x = 0;
    }
}
