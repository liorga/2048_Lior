package com.lior.states;
import com.lior.gameobjects.Grid;

/**
 * The WonState class is used to define a possible state of the game.
 * It is the state where the player has won according to a win condition .
 */
public class WonState implements GameState{

	private static WonState instance = new WonState();

	private WonState() {		
	}

	public static WonState getInstance() {
		return instance;
	}

	@Override
	public void update(Grid grid) {
	}

}
