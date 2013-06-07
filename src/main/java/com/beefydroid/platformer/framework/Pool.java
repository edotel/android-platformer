package com.beefydroid.platformer.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 7/06/13.
 */
public class Pool < T > {
    public interface PoolObjectFactory < T > {
        public T createObject();

    }

    //Store for pooled objects
    private final List < T > freeObjects;
    //Used to generate new instances of the type held by the class
    private final PoolObjectFactory < T > factory;
    //maximum number of objects allowed in this pool
    private final int maxSize;

    //Instantiate
    public Pool(PoolObjectFactory < T > factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList < T > (maxSize);
    }

    //Returns a new instance of the object
    //Or returns an object that's already in the freeObjects
    //Array list
    public T newObject() {
        T object = null;
        if(freeObjects.isEmpty()){
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        return object;
    }

    //Re-insert objects that we no longer use
    //If the list is full, object won't be added, and will be left to the garbage collector
    public void free(T object){
        if(freeObjects.size() < maxSize){
            freeObjects.add(object);
        }
    }
}
