package com.lior.commands;


import com.lior.gameobjects.Grid;

/**
 * The MoveDownCommand executes the moveDown command of the TileHandler.
 */
public class MoveDownCommand extends Command {
	/**
	 * Creates a MoveDownCommand.
	 * 
	 * @param grid The grid to move on.
	 */
	public MoveDownCommand(Grid grid) {
		super(grid);
	}

	/**
	 * Calls the moveDown method and adds the previous grid to the undo stack.
	 */
	@Override
	public void execute() {
		tileHandler.moveDown();
		updateAndSpawn();
	}

}