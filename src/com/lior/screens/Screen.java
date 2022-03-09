package com.lior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.lior.Logger;
import com.lior.drawbehavior.DrawBehavior;
import com.lior.gameobjects.Grid;

public abstract class Screen implements Disposable {
    /** The singleton reference to the Logger instance. */
    private static Logger logger = Logger.getInstance();

    /** Get current class name, used for logging output. */
    private final String className = this.getClass().getSimpleName();

    /** True if the stage of the screen contains actors of an overlay. */
    private boolean hasOverlay;

    /** The scene graph. */
    protected Stage stage;

    /** The DrawBehavior variable to determine the draw implementation */
    protected DrawBehavior drawbehavior;

    /**
     * Called when the screen is shown. Used for initialization.
     */
    public void create() {
        logger.debug(className, "Creating window...");
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Draws the screen with the help of the DrawBehavior implementations.
     */
    public void draw() {
        drawbehavior.draw();
    }

    /**
     * Sets this Screen's drawing behavior.
     * @param newDrawBehavior The new drawing behavior.
     */
    public void setDrawBehavior(DrawBehavior newDrawBehavior) {
        drawbehavior = newDrawBehavior;
    }

    /**
     * Updates the screen.
     */
    public void update() {
        stage.act();
    }

    /**
     * Adds a Lost or Won overlay, depending on whether the game is won, lost,
     * and if it's in multiplayer mode.
     */
    public void addLWOverlay(boolean isWon, Grid grid) {
        if (isWon) {
            new WinScreen(this, grid);
        }
        else {
            new LoseScreen(this, grid);
        }
        hasOverlay = true;
    }

    /**
     * Restarts the current Screen, removing all actors and re-adding the wanted
     * actors.
     */
    public void restart() {
        stage.getActors().clear();
        create();
        hasOverlay = false;
    }

    @Override
    public void dispose() {
        logger.debug(className, "Closing window...");
        stage.dispose();
    }

    /**
     * @return The stage.
     */
    public Stage getStage() {
        return stage;
    }

}