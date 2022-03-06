package com.lior.states;
import com.lior.Logger;
import com.lior.handlers.ScreenHandler;
import com.lior.TwentyFourtyGame;
import com.lior.gameobjects.Grid;


/**
 * The RunningState class is used to define a possible state of the game. It is
 * the state where the game is active and the player has not lost yet.
 */
public class RunningState implements GameState {
	/** Get current class name, used for logging output. */
	private static final String CLASSNAME = RunningState.class.getSimpleName();

	/** The singleton reference to the Logger instance. */
	private static RunningState instance = new RunningState();

	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();

	/** The singleton reference to the ScreenHandler class. */
	private static ScreenHandler screenHandler = ScreenHandler.getInstance();

	/** Overrides the default constructor. */
	private RunningState() {
	}

	/** @return The singleton instance of the state */
	public static RunningState getInstance() {
		return instance;
	}

	@Override
	public void update(Grid grid) {
		if (grid.getCurrentHighestTile() == 11) {
			TwentyFourtyGame.setState(WonState.getInstance());
			screenHandler.getScreen().addLWOverlay(false, true, grid);
		} else if (grid.getPossibleMoves() == 0) {
			TwentyFourtyGame.setState(LostState.getInstance());
			screenHandler.getScreen().addLWOverlay(false, false, grid);
		}
	}


	/** For testing purposes only */
	public void setScreenHandler(ScreenHandler handler) {
		screenHandler = handler;
	}
}