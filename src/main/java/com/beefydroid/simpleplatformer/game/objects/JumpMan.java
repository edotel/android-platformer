package com.beefydroid.simpleplatformer.game.objects;

import com.beefydroid.simpleplatformer.framework.DynamicGameObject;
import com.beefydroid.simpleplatformer.game.levels.test.TestWorld;

/**
 * Created by Leo on 13/06/13.
 */
public class JumpMan extends DynamicGameObject{

    public static final int STATE_IDLE = 0x01;      // 0001
    public static final int STATE_JUMPING = 0x02;   // 0010
    public static final int STATE_LEFT = 0x04;      // 0100
    public static final int STATE_RIGHT = 0x08;     // 1000
    public static final float JUMP_VELOCITY = 11;
    public static final float MOVE_VELOCITY = 1.5f;
    public static final float WIDTH = 0.8f;
    public static final float HEIGHT = 0.8f;

   // MOVEMENT_STATES state;
    int state;
    float stateTime;
    boolean grounded = false;

    public JumpMan(float x, float y){
        super(x, y, WIDTH, HEIGHT);
        state = STATE_IDLE;
        stateTime = 0;
    }

    public void update(float deltaTime){
        if(!grounded){
            //Add acceleration to velocity
            velocity.add(TestWorld.gravity.x * deltaTime, TestWorld.gravity.y * deltaTime);

            //Add velocity to position
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        } else {
            velocity.y = 0;
        }

        // If doing something interesting
        if (state != STATE_IDLE){
            // If jumping
            if ((state & STATE_JUMPING) == STATE_JUMPING){
                velocity.y = JUMP_VELOCITY;
            }
            // If moving left
            if ((state & STATE_LEFT) == STATE_LEFT){
                velocity.x = MOVE_VELOCITY;
            }
            // If moving right
            if ((state & STATE_RIGHT) == STATE_RIGHT){
                velocity.x = MOVE_VELOCITY;
            }
            //Add acceleration to velocity
           // velocity.add(TestWorld.gravity.x * deltaTime, TestWorld.gravity.y * deltaTime);

            //Add velocity to position
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        } else {
            // If idling, slow down
            if (velocity.x > 0.01 || velocity.x < 0.01)
            {
                velocity.sub(velocity.x * deltaTime, 0);
            }
            if (velocity.y > 0.01 || velocity.y < 0.01){
                velocity.sub(0, velocity.y * deltaTime);
            }

        }

        //update bounds
        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
        grounded = false;
    }
    public void jump(){
        // If idle, remove idle state
        if (state == STATE_IDLE){
            state = STATE_JUMPING;
        }
        // Otherwise add jumping state to state
        else {
            state &= STATE_JUMPING;
        }
    }
    public void moveLeft(){
        //If already moving right, cancel out and remove moving right state
        if ((state & STATE_RIGHT) == STATE_RIGHT){
            state -= STATE_RIGHT;
        }
        // If idle, remove idle state
        else if (state == STATE_IDLE){
            state = STATE_LEFT;
        }
        // Otherwise add move left state
        else {
            state &= STATE_RIGHT;
        }
    }
    public void moveRight(){
        // If moving left, cancel out and remove left state
        if ((state & STATE_LEFT) == STATE_LEFT){
            state -= STATE_LEFT;
        }
        // If idle, remove idle state
        if (state == STATE_IDLE) {
            state = STATE_RIGHT;
        }
        // Otherwise add move right state
        else {
            state &= STATE_RIGHT;
        }
    }
    public int getState(){
        return state;
    }
    public float getStateTime(){
        return stateTime;
    }
    public void setGrounded(boolean grounded){
        this.grounded = grounded;
    }
}
