package com.lior;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lior.drawables.DrawableGrid;
import com.lior.drawables.DrawableTile;
import com.lior.gameobjects.AssetHandler;
import com.lior.gameobjects.Grid;
import com.lior.states.GameState;
import com.lior.states.LostState;
import com.lior.states.WonState;

/**
 * A button that calls the restart method of the Grid and ScreenHandler classes.
 * Extends the TextButton class from the GDX library.
 */
public class RestartButton extends TextButton {
	/** Constructs a new RestartButton. */
	public RestartButton(final Grid grid, boolean ingame) {
		super("Restart", AssetHandler.getInstance().getSkin());
		if (ingame) {
			TextButtonStyle style = AssetHandler.getInstance().getSkin()
					.get("small", TextButtonStyle.class);
			this.setStyle(style);
			this.setHeight(50);
			this.setWidth(DrawableTile.TILE_WIDTH);
			this.setX(DrawableTile.TILE_X - DrawableTile.TILE_WIDTH / 2
					- TwentyFourtyGame.GAP / 2 + 2
					* (DrawableTile.TILE_WIDTH + TwentyFourtyGame.GAP));
		} else {
			this.setX(TwentyFourtyGame.GAME_WIDTH / 2 - this.getWidth() / 2);
		}
		this.setY(DrawableGrid.GRID_Y / 2 - this.getHeight() / 2);

		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameState state = TwentyFourtyGame.getState();
				grid.restart();
				if (state instanceof LostState || state instanceof WonState) {
					ScreenHandler.getInstance().restart();
				}
			}
		});
	}
}
