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

	private static RunningState instance = new RunningState();

	private static ScreenHandler screenHandler = ScreenHandler.getInstance();

	private RunningState() {
	}

	public static RunningState getInstance() {
		return instance;
	}

	@Override
	public void update(Grid grid) {
		if (grid.getCurrentHighestTile() == 11) {
			TwentyFourtyGame.setState(WonState.getInstance());
			screenHandler.getScreen().addLWOverlay( true, grid);
		} else if (grid.getPossibleMoves() == 0) {
			TwentyFourtyGame.setState(LostState.getInstance());
			screenHandler.getScreen().addLWOverlay( false, grid);
		}
	}

}