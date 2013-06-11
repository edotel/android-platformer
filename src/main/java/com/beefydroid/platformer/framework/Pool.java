/**
 * Created by Leo on 7/06/13. (code borrowed from AndroidGames book)
 *
 * Object Pool.
 * Useful for saving memory when a list of objects is repeatedly called, such as
 * with Input events, like TouchEvent.
 *
 * It saves previous instances to the pool, to be re-used later instead of creating a new instance
 * and relying on the garbage collector to remove the old ones. (Garbage collector is lazy)
 *
 */
package com.beefydroid.platformer.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool < T > {
    public interface PoolObjectFactory < T > {
        public T createObject();
    }

    //Store for pooled objects
    private final List < T > freeObjects;

    //Used to generate new instances of the type held by the class
    private final PoolObjectFactory < T > factory;

    //maximum number of objects allowed in this pool (so we don't run out of memory)
    private final int maxSize;

    //Instantiate
    public Pool(PoolObjectFactory < T > factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;

        //Create pool with the max size
        this.freeObjects = new ArrayList < T > (maxSize);
    }

    // Returns a new object.
    // If the list is empty, it creates a new one. Otherwise, more likely, it pulls one out of the
    // freeObjects (Pool) ArrayList.
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
