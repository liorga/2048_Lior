package com.lior.gameobjects;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.lior.handlers.AssetHandler;
import com.lior.gameobjects.Grid;

import java.util.Observable;
import java.util.Observer;

public class Scores extends Group implements Observer {
    /** Coordinates and offsets used to position the labels. */
    private static final int LABEL_X = 100;
    private static final int LABEL_Y = 520;
    private static final int HEIGHT = 70;
    private static final int SCORE_WIDTH = 140;
    private static final int HIGHEST_WIDTH = 90;

    /** The singleton AssetHandler instance used to access our assets. */
    private AssetHandler assetHandler = AssetHandler.getInstance();

    /** Labels to display scores. */
    private Label scoreLabel;
    private Label highScoreLabel;
    private Label highestTileLabel;

    /** Keeps track of the current high score. */
    private int highScore;

    /** The highest tile value saved. */
    private int highestTile;

    /**
     * Creates a new ScoreDisplay object. Automatically creates all textures and
     * labels and positions them.
     */
    public Scores() {
        scoreLabel = new Label(Integer.toString(0),
                assetHandler.getSkin(), "score");
        highScoreLabel = new Label(Integer.toString(highScore),
                assetHandler.getSkin(), "highscore");
        highestTileLabel = new Label(Integer.toString(highestTile),
                assetHandler.getSkin(), "highest");

        setLabelMetrics();
        addLabelsToGroup();
        setLabelLocations();
    }

    /**
     * Sets the Label width and height
     */
    private void setLabelMetrics() {
        scoreLabel.setHeight(HEIGHT);
        scoreLabel.setWidth(SCORE_WIDTH);

        highScoreLabel.setHeight(HEIGHT);
        highScoreLabel.setWidth(SCORE_WIDTH);

        highestTileLabel.setHeight(HEIGHT);
        highestTileLabel.setWidth(HIGHEST_WIDTH);
    }

    /**
     * Sets the label locations. This is called on acting to update the size
     * when a score changes.
     */
    private void setLabelLocations() {
        scoreLabel.setX(LABEL_X);
        scoreLabel.setY(LABEL_Y);
        scoreLabel.setAlignment(Align.bottom, Align.center);

        highScoreLabel.setX(LABEL_X + SCORE_WIDTH + 15);
        highScoreLabel.setY(LABEL_Y);
        highScoreLabel.setAlignment(Align.bottom, Align.center);

        highestTileLabel.setX(LABEL_X + 2 * SCORE_WIDTH + 2
                * 15);
        highestTileLabel.setY(LABEL_Y);
        highestTileLabel.setAlignment(Align.bottom, Align.center);
    }

    /** Adds the score labels to the group */
    private void addLabelsToGroup() {
        this.addActor(scoreLabel);
        this.addActor(highScoreLabel);
        this.addActor(highestTileLabel);
    }

    /**
     * Sets the current high score to the value provided, on the condition that
     * it is actually higher than the previous highscore.
     *
     * @param highScore
     *            The new high score.
     */
    public void setHighScore(int highScore) {
        if (this.highScore < highScore) {
            this.highScore = highScore;
        }
    }

    /**
     * Sets the highest tile value to the value provided, on the condition that
     * it is actually the highest value.
     *
     * @param highestTile
     *            The new highest tile value.
     */
    public void setHighestTile(int highestTile) {
        if (this.highestTile < highestTile) {
            this.highestTile = highestTile;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Grid grid = (Grid) o;
        int currentHighest = (int) Math.pow(2, grid.getCurrentHighestTile());
        setHighestTile(currentHighest);
        setHighScore(grid.getScore());

        highestTileLabel.setText(Integer.toString(highestTile));
        highScoreLabel.setText(Integer.toString(highScore));
        scoreLabel.setText(Integer.toString(grid.getScore()));
    }
}