/**
 * Created by Leo on 7/06/13.
 * Abstract Screen class - meant to be extended by other classes (such as GLScreen) and then the
 * respective game screens (main menu, game, settings, etc.)
 *
 */
package com.beefydroid.platformer.framework;

public abstract class Screen {

    protected final Game game;

    public Screen(Game game) {
        // Get access to low-level modules of the Game interface for playing audio,
        // drawing graphics, receiving input, and file IO. Can also change screen.
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
