package com.lior.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lior.buttons.RestartButton;
import com.lior.buttons.SolveButton;
import com.lior.TwentyFourtyGame;
import com.lior.drawables.DrawableGrid;
import com.lior.gameobjects.Scores;
import com.lior.drawbehavior.DrawBeige;
import com.lior.gameobjects.Grid;
import com.lior.handlers.InputHandler;
import com.lior.handlers.ProgressHandler;
import com.lior.states.RunningState;

public class GameScreen extends Screen {

    private Grid grid;

    private Scores scores;

    private DrawableGrid drawableGrid;

    private RestartButton restartButton;

    private SolveButton solveButton;

    private ProgressHandler progressHandler = ProgressHandler.getInstance();

    private InputHandler inputHandler;

    public GameScreen() {
        stage = new Stage();
        grid = progressHandler.loadGame();
        drawableGrid = new DrawableGrid(grid.getTiles());
        restartButton = new RestartButton(grid, true);
        solveButton = new SolveButton(grid);
        scores = new Scores();

        grid.addObserver(scores);
        this.setDrawBehavior(new DrawBeige(stage));

        inputHandler = new InputHandler(grid);
    }

    @Override
    public void create() {
        super.create();
        stage.addListener(inputHandler);
        /* Create the main group and pack everything in it. */
        stage.addActor(drawableGrid);
        stage.addActor(restartButton);
        stage.addActor(solveButton);
        stage.addActor(scores);

        /* After creating the screen, start the game. */
        TwentyFourtyGame.setState(RunningState.getInstance());
    }

    @Override
    public void update() {
        super.update();
        TwentyFourtyGame.getState().update(grid);
    }

    @Override
    public void dispose() {
        progressHandler.saveGame(grid);
        super.dispose();
    }
}