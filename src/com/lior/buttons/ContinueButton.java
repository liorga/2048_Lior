package com.lior.buttons;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lior.TwentyFourtyGame;
import com.lior.handlers.AssetHandler;
import com.lior.handlers.ScreenHandler;
import com.lior.states.ContinuingState;

/**
 * A button to continue playing after having made a 2048 value tile. Extends the
 * TextButton class from the GDX library.
 */
public class ContinueButton extends TextButton {
	/** Constructs a new ContinueButton. */
	public ContinueButton() {
		super("Continue!", AssetHandler.getInstance().getSkin());
		this.setX(TwentyFourtyGame.GAME_WIDTH / 2 - getPrefWidth() / 2);
		this.setY(this.getPrefHeight() + 2 * TwentyFourtyGame.GAP);

		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenHandler.getInstance().restart();
				TwentyFourtyGame.setState(ContinuingState.getInstance());
			}
		});
	}
}
