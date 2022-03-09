package com.lior.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.lior.Logger;
import com.lior.aisolver.GridSolver;
import com.lior.gameobjects.Grid;

public class PreferenceHandler {

    private static PreferenceHandler instance = new PreferenceHandler();

    private static Preferences prefs = Gdx.app.getPreferences("2048");

    private PreferenceHandler() {

    }
    public static PreferenceHandler getInstance() {
        return instance;
    }
    /**
     * @return The score saved from the previous game.
     */
    public int getScore() {
        return prefs.getInteger("score");
    }


    public void setScore(int score) {
        prefs.putInteger("score", score);
        prefs.flush();
    }

    public int getHighscore() {
        return prefs.getInteger("highscore");
    }

    public void setHighscore(int highscore) {
        prefs.putInteger("highscore", highscore);
        prefs.flush();
    }

    public int getHighestTile() {
        return prefs.getInteger("highest");
    }

    public void setHighest(int highest) {
        prefs.putInteger("highest", highest);
        prefs.flush();
    }

    public String getGrid() {
        return prefs.getString("grid");
    }

    public void setGrid(String grid) {
        prefs.putString("grid", grid);
        prefs.flush();
    }

    public Grid.Difficulty getDifficulty() {
        if (!prefs.contains("difficulty")) {
            return Grid.Difficulty.RANDOM;
        }
        String difficulty = prefs.getString("difficulty");
        return Grid.Difficulty.valueOf(difficulty);
    }

    public int getSolverDelay() {
        int type = prefs.getInteger("solverDelay");
        return type <= 0 ? 300 : type;
    }
}