package com.lior.commands;

import com.lior.gameobjects.Grid;

/**
 * The MoveUpCommand executes the moveUp command of the TileHandler.
 */
public class MoveUpCommand extends Command {
	/**
	 * Creates a MoveUpCommand.
	 * 
	 * @param grid The grid to move on.
	 */
	public MoveUpCommand(Grid grid) {
		super(grid);
	}

	/**
	 * Calls the moveUp method and adds the previous grid to the undo stack.
	 */
	@Override
	public void execute() {
		tileHandler.moveUp();
		updateAndSpawn();
	}
}