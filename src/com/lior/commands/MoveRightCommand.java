package com.lior.commands;

import com.lior.gameobjects.Grid;


/**
 * The MoveRightCommand executes the moveRight command of the TileHandler.
 */
public class MoveRightCommand extends Command {
	/**
	 * Creates a MoveRightCommand.
	 * 
	 * @param grid The grid to move on.
	 */
	public MoveRightCommand(Grid grid) {
		super(grid);
	}

	/**
	 * Calls the moveRight method and adds the previous grid to the undo stack.
	 */
	@Override
	public void execute() {
		tileHandler.moveRight();
		updateAndSpawn();
	}
}
