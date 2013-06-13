package com.beefydroid.simpleplatformer.framework.gl;

/**
 * Created by Leo on 12/06/13.
 * Contains the boundaries and position of a region of a texture. Useful for spritesheets.
 */
public class TextureRegion {
    public final float u1, u2;
    public final float v1, v2;
    Texture texture;

    public TextureRegion(Texture texture, float x, float y, float width,
                         float height) {
        this.u1 = x / texture.width;
        this.v1 = y / texture.height;
        this.u2 = this.u1 + width / texture.width;
        this.v2 = this.v1 + height / texture.height;
        this.texture = texture;

    }
}
