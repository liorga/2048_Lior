package com.lior.states;
import com.lior.gameobjects.Grid;

/**
 * The GameState interface is used to create a hierarchy of possible states in the game.
 * The state methods are then called polymorphically.
 */
public interface GameState {
	/**
	* The update(grid) method is used to update singleplayer states.
	*/
	public void update(Grid grid);


}
