package com.lior.aisolver;


import com.lior.Direction;
import com.lior.gameobjects.Grid;

public interface Solver {
	/**
	 * Find a suitable move on a grid. This will select one of the moves LEFT,
	 * DOWN, RIGHT or UP.
	 * 
	 * @param grid
	 *            The grid to find a move on.
	 * @param depth
	 *            The recursion depth. Depending on the implementation, this
	 *            will either be used or ignored.
	 * @return The best direction to move into.
	 */
	public Direction findMove(Grid grid, int depth);
}
