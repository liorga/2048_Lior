package com.lior;



import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lior.aisolver.GridSolver;
import com.lior.drawables.DrawableTile;
import com.lior.gameobjects.AssetHandler;
import com.lior.gameobjects.Grid;

/**
 * A button to call a solver to solve the game. Extends the TextButton class
 * from the GDX library.
 */
public class SolveButton extends TextButton {
	/** The singleton reference to the GridSolver instance. */
	private static GridSolver solver = GridSolver.getInstance();

	/** Constructs a new SolveButton. */
	public SolveButton(final Grid grid) {
		super("Solve", AssetHandler.getInstance().getSkin(), "small");
		
		this.setHeight(50);
		this.setWidth(DrawableTile.TILE_WIDTH);
		this.setX(DrawableTile.TILE_X - DrawableTile.TILE_WIDTH / 2
				- TwentyFourtyGame.GAP / 2);
		this.setY(100 / 2 - this.getHeight() / 2);

		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!solver.isRunning()) {
					solver.start(grid);
				} else {
					solver.stop();
				}
			}
		});
	}
}
