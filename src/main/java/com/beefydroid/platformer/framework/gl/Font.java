package com.beefydroid.platformer.framework.gl;

/**
 * Created by Leo on 12/06/13.
 */
public class Font {
    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];

    public Font(Texture texture, int offsetX, int offsetY, int glyphsPerRow, int glyphWidth, int glyphHeight) {
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        for(int i = 0; i < 96; i++){
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            //Increase by one character
            x += glyphWidth;
            //If reached end of row, move down one row
            if (x == offsetX + glyphsPerRow * glyphWidth){
                x = offsetX;
                y += glyphHeight;
            }
        }
    }
    public void drawText(SpriteBatcher batcher, String text, float x, float y){
        int len = text.length();
        for(int i = 0; i < len; i++){
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1){
                continue;
            }

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
        }
    }
}
