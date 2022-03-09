package com.lior.handlers;

import com.lior.screens.Screen;
import com.lior.aisolver.GridSolver;

public class ScreenHandler {

    private static ScreenHandler instance = new ScreenHandler();

    /** The screen currently being managed, which is also the
     * screen currently on display. */
    private Screen screen;

    private ScreenHandler() {

    }

    public static ScreenHandler getInstance() {
        return instance;
    }

    /**
     * Sets the specified screen to be the current screen. Disposes the current
     * screen if it is not null and resizes the screen if necessary.
     */
    public void set(Screen screen) {
        if (this.screen != null) {
            dispose();
        }
        this.screen = screen;
        screen.create();
    }

    /**
     * Disposes cleanly of the screen.
     * */
    public void dispose() {
        if (GridSolver.getInstance().isRunning()) {
            GridSolver.getInstance().stop();
        }
        screen.dispose();
    }

    /**
     * Draws the screen.
     */
    public void draw() {
        screen.draw();
    }

    /**
     * Updates the screen.
     */
    public void update() {
        screen.update();
    }

    /**
     * Restarts the current screen.
     */
    public void restart() {
        screen.restart();
    }

    /**
     * @return The current screen.
     */
    public Screen getScreen() {
        return screen;
    }
}