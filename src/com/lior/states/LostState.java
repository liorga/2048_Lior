package com.lior.states;
import com.lior.gameobjects.Grid;


/**
 * The LostState class is used to define a possible state of the game.
 * It is the state where the player has lost according to a lose condition.
 */
public class LostState implements GameState{

	private static LostState instance = new LostState();

	private LostState() {}

	public static LostState getInstance() {
		return instance;
	}

	@Override
	public void update(Grid grid) {	
	}

}
