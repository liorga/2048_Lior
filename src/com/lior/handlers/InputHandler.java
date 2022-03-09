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

    private static final String CLASSNAME = InputHandler.class.getSimpleName();
    private static Logger logger = Logger.getInstance();
    private Command command;
    /**
     * A reference to the current Grid, so the called objects can interact with
     * it.
     */
    private Grid grid;

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

    public void executeCommand(Command command) {
        this.command = command;
        this.command.execute();
    }

}