/**
 * Created by Leo on 7/06/13.
 *
 * Basic fundamental classes for a game.
 *
 */
package com.beefydroid.simpleplatformer.framework;

public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
}
