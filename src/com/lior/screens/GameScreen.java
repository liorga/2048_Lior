package com.lior.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lior.buttons.RestartButton;
import com.lior.buttons.SolveButton;
import com.lior.TwentyFourtyGame;
import com.lior.drawables.DrawableGrid;
import com.lior.drawables.Scores;
import com.lior.drawbehavior.DrawBeige;
import com.lior.gameobjects.Grid;
import com.lior.handlers.InputHandler;
import com.lior.handlers.ProgressHandler;
import com.lior.states.RunningState;

public class GameScreen extends Screen {
    /** The grid holding all the Tiles. */
    private Grid grid;

    /** The score tiles above the Grid. */
    private Scores scores;

    /** The grid that is actually drawn. */
    private DrawableGrid drawableGrid;

    /** The button to ask for a hint. */
    //private HintButton hintButton;

    /** The button to undo the last move. */
    //private UndoButton undoButton;

    /** The button to redo the last move. */
   // private RedoButton redoButton;

    /** The button to restart the current game. */
    private RestartButton restartButton;

    /** The button to let a solver solve the game. */
    private SolveButton solveButton;

    /** The singleton reference to the ProgressHandler class. */
    private ProgressHandler progressHandler = ProgressHandler.getInstance();

    /** The InputHandler receives and processes the received input. */
    private InputHandler inputHandler;

    /** Constructs a new GameScreen. */
    public GameScreen() {
        stage = new Stage();
        grid = progressHandler.loadGame();
        drawableGrid = new DrawableGrid(grid.getTiles());
//        hintButton = new HintButton(grid);
        restartButton = new RestartButton(grid, true);
        solveButton = new SolveButton(grid);
//        undoButton = new UndoButton(grid);
//        redoButton = new RedoButton(grid);
        scores = new Scores();

        grid.addObserver(scores);
        this.setDrawBehavior(new DrawBeige(stage));

        inputHandler = new InputHandler(grid);
    }

    /** Constructor to insert Mock objects. For testing only. */
    public GameScreen(Stage stage, Grid grid,Scores scores) {
        this.stage = stage;
        this.grid = grid;
        this.scores = scores;
        grid.addObserver(scores);
        this.setDrawBehavior(new DrawBeige(stage));
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