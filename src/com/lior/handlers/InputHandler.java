package com.lior.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.lior.Logger;
import com.lior.screens.MenuScreen;
import com.lior.commands.*;
import com.lior.gameobjects.Grid;

public class InputHandler extends InputListener {
    /** Get current class name, used for logging output. */
    private static final String CLASSNAME = InputHandler.class.getSimpleName();

    /**
     * A reference to the current Grid, so the called objects can interact with
     * it.
     */
    private Grid grid;

    /** The singleton reference to the Logger instance. */
    private static Logger logger = Logger.getInstance();

    /** The current command that can be executed. */
    private Command command;

    /**
     * Creates a new InputHandler instance.
     *
     * @param grid
     *            A reference to the current Grid.
     */
    public InputHandler(Grid grid) {
        this.grid = grid;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.DPAD_DOWN:
                logger.debug(CLASSNAME, "User pressed key: DOWN");
                executeCommand(new MoveDownCommand(grid));
                return true;
            case Input.Keys.DPAD_UP:
                logger.debug(CLASSNAME, "User pressed key: UP");
                executeCommand(new MoveUpCommand(grid));
                return true;
            case Input.Keys.DPAD_LEFT:
                logger.debug(CLASSNAME, "User pressed key: LEFT");
                executeCommand(new MoveLeftCommand(grid));
                return true;
            case Input.Keys.DPAD_RIGHT:
                logger.debug(CLASSNAME, "User pressed key: RIGHT");
                executeCommand(new MoveRightCommand(grid));
                return true;
            case Input.Keys.ESCAPE:
                logger.debug(CLASSNAME, "User pressed key: ESCAPE");
                ProgressHandler.getInstance().saveGame(grid);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        ScreenHandler.getInstance().set(new MenuScreen());
                    }
                });
                return true;
        }
        return false;
    }

    /** Sets and executes the provided command. */
    public void executeCommand(Command command) {
        this.command = command;
        this.command.execute();
    }

    /** Returns the current command. */
    public Command getCommand() {
        return command;
    }
}