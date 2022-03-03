package com.lior.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.lior.Logger;
import com.lior.aisolver.GridSolver;
import com.lior.gameobjects.Grid;

public class PreferenceHandler {
    /** The unique singleton instance of this class. */
    private static PreferenceHandler instance = new PreferenceHandler();

    /** The Preferences object to save and load everything. */
    private static Preferences prefs = Gdx.app.getPreferences("2048");

    /** Overrides the default constructor. */
    private PreferenceHandler() {
    }

    /**
     * @return The singleton instance of the PreferenceHandler
     */
    public static PreferenceHandler getInstance() {
        return instance;
    }

    /**
     * @return The Preferences object.
     */
    public Preferences getPrefs() {
        return prefs;
    }

    /**
     * Initializes the scores to zero in case these can not be found, does
     * nothing otherwise.
     */
    public void initScores() {
        if (!prefs.contains("score")) {
            prefs.putInteger("score", 0);
            prefs.flush();
        }
        if (!prefs.contains("highscore")) {
            prefs.putInteger("highscore", 0);
            prefs.flush();
        }
        if (!prefs.contains("highest")) {
            prefs.putInteger("highest", 1);
            prefs.flush();
        }
    }

    /**
     * @return The score saved from the previous game.
     */
    public int getScore() {
        return prefs.getInteger("score");
    }

    /**
     * Saves its parameter as the score of the current game.
     *
     * @param score
     *            The value to save.
     */
    public void setScore(int score) {
        prefs.putInteger("score", score);
        prefs.flush();
    }

    /**
     * @return The saved highscore.
     */
    public int getHighscore() {
        return prefs.getInteger("highscore");
    }

    /**
     * Saves its parameter as the new highscore.
     *
     * @param highscore
     *            The value of the new highscore.
     */
    public void setHighscore(int highscore) {
        prefs.putInteger("highscore", highscore);
        prefs.flush();
    }

    /**
     * @return The value of the highest tile ever reached.
     */
    public int getHighestTile() {
        return prefs.getInteger("highest");
    }

    /**
     * Saves its parameter as the highest tile value ever reached.
     *
     * @param highest
     *            The value of the highest tile ever reached.
     */
    public void setHighest(int highest) {
        prefs.putInteger("highest", highest);
        prefs.flush();
    }

    /**
     * @return The grid saved from the previous game.
     */
    public String getGrid() {
        return prefs.getString("grid");
    }

    /**
     * Saves its parameter as the grid from the current game.
     *
     * @param grid
     *            The String indicating the contents of the grid.
     */
    public void setGrid(String grid) {
        prefs.putString("grid", grid);
        prefs.flush();
    }

    /** @return Returns the saved LogLevel, null if no level is saved. */
    public Logger.LogLevel getLogLevel() {
        if (!prefs.contains("loglevel")) {
            return Logger.LogLevel.ALL;
        }
        String level = prefs.getString("loglevel");
        return Logger.LogLevel.valueOf(level);
    }

    /** Sets the LogLevel as specified by the user. */
    public void setLogLevel(String level) {
        prefs.putString("loglevel", level);
        prefs.flush();
    }

    /**
     * @return True if the user has enabled logging to file, false otherwise.
     */
    public boolean isLogFileEnabled() {
        return !prefs.getString("logfile").equals("");
    }

    /** Enables or disables logging to file, as specified by the user. */
    public void setLogFileEnabled(boolean isEnabled) {
        if (isEnabled) {
            prefs.putString("logfile", "2048");
        } else {
            prefs.remove("logfile");
        }
        prefs.flush();
    }

    /** @return The difficulty with which tiles will be spawned. */
    public Grid.Difficulty getDifficulty() {
        if (!prefs.contains("difficulty")) {
            return Grid.Difficulty.RANDOM;
        }
        String difficulty = prefs.getString("difficulty");
        return Grid.Difficulty.valueOf(difficulty);
    }

    /** Sets the difficulty with which tiles will be spawned. */
    public void setDifficulty(String difficulty) {
        prefs.putString("difficulty", difficulty);
        prefs.flush();
    }

    /**
     * @return The Strategy saved, or HUMAN if no strategy is saved.
     */
    public GridSolver.Strategy getSolverStrategy() {
        String type = prefs.getString("solver");
        if (type.contains("EXPECTI")) {
            return GridSolver.Strategy.EXPECTIMAX;
        } else {
            return GridSolver.Strategy.HUMAN;
        }
    }

    /** Sets the solver strategy to the type specified. */
    public void setSolver(String type) {
        prefs.putString("solver", type);
        prefs.flush();
    }

    /** @return The delay the solver should have between its moves. */
    public int getSolverDelay() {
        int type = prefs.getInteger("solverDelay");
        return type <= 0 ? 300 : type;
    }

    /** Sets the delay between the moves of the solver. */
    public void setSolverDelay(int delay) {
        prefs.putInteger("solverDelay", delay);
        prefs.flush();
    }
}