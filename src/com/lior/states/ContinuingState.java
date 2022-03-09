package com.lior.states;
import com.lior.handlers.ScreenHandler;
import com.lior.gameobjects.Grid;

/**
 * The ContinuingState class is used to define a possible state of the game. It
 * is the state where the player has chosen to continue after he has won in a game
 */
public class ContinuingState implements GameState{

	private static ContinuingState instance = new ContinuingState();

	private ContinuingState() {
	}

	public static ContinuingState getInstance() {
		return instance;
	}

	@Override
	public void update(Grid grid) {
	}
}
