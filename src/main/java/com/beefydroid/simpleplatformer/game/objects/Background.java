package com.beefydroid.simpleplatformer.game.objects;

import com.beefydroid.simpleplatformer.framework.DynamicGameObject;

/**
 * Created by Leo on 27/06/13.
 */
public class Background extends DynamicGameObject{
    public Background(float x, float y, float width, float height){
        super(x, y, width, height);

    }

    public void update(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }
}
