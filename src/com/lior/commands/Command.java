package com.lior.commands;


import com.lior.gameobjects.Grid;
import com.lior.gameobjects.TileHandler;

/**
 * The Command class defines an abstract execute method, which enables subclasses
 * to define and perform various "commands".
 */
public abstract class Command {
	/** The current grid on which the move has to take place. */
	protected Grid grid;

	/** The current TileHandler that has to conduct the move. */
	protected TileHandler tileHandler;

	/**
	 * Common constructor for all subclasses.
	 * @param grid The grid to execute the command on.
	 */
	public Command(Grid grid) {
		this.tileHandler = grid.getTileHandler();
		this.grid = grid;
	}

	/**
	 * Calls grid to update everything after a move and resets the TileHandler.
	 */
	public void updateAndSpawn() {
		grid.updateAfterMove();
		if (tileHandler.isMoveMade()) {
			tileHandler.reset();
		}
	}

	/**
	 * The subclasses have to implement an execute method.
	 */
	public abstract void execute();
}
