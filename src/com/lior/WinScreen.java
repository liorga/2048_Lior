package com.lior;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.lior.gameobjects.AssetHandler;
import com.lior.gameobjects.Grid;

/**
 * The WinScreen is displayed when the player has won. It is semi-transparent,
 * and offers the ability to restart or continue playing.
 */
public class WinScreen {
	/** The background image. */
	private Image image;

	/** The button used to put the game into continuing state. */
	private ContinueButton continueButton;

	/** The button used to restart the game. */
	private RestartButton restartButton;

	/** The stage of the parent screen. */
	private Stage stage;

	/** Constructs a new WinScreen. */
	public WinScreen(Screen parent, Grid grid) {
		this.stage = parent.getStage();

		image = new Image(AssetHandler.getInstance().getSkin(), "wonoverlay");
		restartButton = new RestartButton(grid, false);
		continueButton = new ContinueButton();

		addActors();
	}

	/** Constructor for testing only */
	public WinScreen(Stage stage, Image image, RestartButton rButton,
			ContinueButton cButton) {
		this.stage = stage;
		this.image = image;
		restartButton = rButton;
		continueButton = cButton;
		addActors();
	}

	/** Adds the actors of this overlay to its parent screen. */
	private void addActors() {
		stage.addActor(image);
		stage.addActor(continueButton);
		stage.addActor(restartButton);
	}
}
