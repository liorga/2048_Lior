package com.lior.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lior.buttons.RestartButton;
import com.lior.handlers.AssetHandler;
import com.lior.gameobjects.Grid;

/**
 * The LoseScreen is displayed when the player has lost. It is semi-transparent,
 * and offers the ability to restart.
 */
public class LoseScreen {

	private Image image;

	private RestartButton restartButton;

	/** The stage of the parent screen. */
	private Stage stage;

	public LoseScreen(Screen parent, Grid grid) {
		stage = parent.getStage();

		image = new Image(AssetHandler.getInstance().getSkin(), "lostoverlay");
		restartButton = new RestartButton(grid, false);

		stage.addActor(image);
		stage.addActor(restartButton);
	}
}
