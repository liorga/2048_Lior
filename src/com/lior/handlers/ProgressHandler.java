package com.lior.handlers;

import com.lior.Logger;
import com.lior.TwentyFourtyGame;
import com.lior.gameobjects.Grid;

public class ProgressHandler {

    private static final String CLASSNAME = ProgressHandler.class.getSimpleName();

    private static ProgressHandler instance = new ProgressHandler();

    private static PreferenceHandler prefsHandler = PreferenceHandler.getInstance();

    private static Logger logger = Logger.getInstance();

    private ProgressHandler() {
    }

    public static ProgressHandler getInstance() {
        return instance;
    }

    /**
     * Calls saveGrid to save the current grid and uses the PreferenceHandler to
     * save the current score, high score and highest tile value ever reached.
     *
     * @param grid
     *            The Grid to save its' current state.
     */
    public void saveGame(Grid grid) {
        logger.info(CLASSNAME, "Saving game to preference file...");

        int highest = grid.getCurrentHighestTile();
        int score = grid.getScore();

        if (!TwentyFourtyGame.isLost()) {
            prefsHandler.setGrid(grid.toString());
            prefsHandler.setScore(score);
        }

        if (highest > prefsHandler.getHighestTile()) {
            prefsHandler.setHighest(highest);
        }

        if (score > prefsHandler.getHighscore()) {
            prefsHandler.setHighscore(score);
        }
    }

    /**
     * Loads the saved grid,
     */
    public Grid loadGame() {
        Grid grid = loadGrid();
        loadGame(grid);
        return grid;
    }

    public Grid loadGame(Grid grid) {
        logger.info(CLASSNAME, "Loading game from preference file...");

        grid.setDifficulty(prefsHandler.getDifficulty());
        grid.updateHighestTile();

        return grid;
    }

    private Grid loadGrid() {
        logger.info(CLASSNAME, "Loading saved grid...");
        String filledTiles = prefsHandler.getGrid();
        /*
         * If no grid is saved, return a default one. Else, fill the grid with
         * the saved tiles.
         */
        if (filledTiles.equals("")) {
            return new Grid(false);
        } else {
            String[] split = filledTiles.split(",");
            if (split.length != 16) {
                return new Grid(false);
            }

            Grid grid = new Grid(true);
            for (int i = 0; i < split.length; i++) {
                int value = Integer.parseInt(split[i]);
                grid.setTile(i, value < 0 ? 0 : value);
            }
            return grid;
        }
    }
}