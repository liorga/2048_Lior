package com.lior.aisolver;

import com.lior.gameobjects.Direction;
import com.lior.Logger;
import com.lior.handlers.PreferenceHandler;
import com.lior.gameobjects.Grid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



/**
 * The GridSolver starts a AI session that will attempt to solve the Grid it is
 * called on.
 */
public class GridSolver implements Runnable {
	/** An enumeration indicating which solver will be used. */
	public enum Strategy {
		HUMAN, EXPECTIMAX
	}

	/** Get current class name, used for logging output. */
	private static final String CLASSNAME = GridSolver.class.getSimpleName();

	/** The singleton reference to this class. */
	private static GridSolver instance = new GridSolver();

	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();

	/** The scheduler and its future tasks used to make moves with fixed delay */
	private ScheduledExecutorService timer;
	private ScheduledFuture<?> future;

	/** The delay between consecutive task executions. */
	private int delay;

	/** The original Grid to calculate for and make a move on. */
	private Grid original;

	/** An indication on whether this solver is running. */
	private boolean running;

	/**
	 * The recursion depth to use. Depending on the used solver, this may be
	 * ignored.
	 */
	private int depth;

	/** The solver used to solve the Grid. */
	private Solver solver;

	/** Constructs a new GridSolver. */
	private GridSolver() {
		this.delay = PreferenceHandler.getInstance().getSolverDelay();
		depth = 6;
		timer = Executors.newScheduledThreadPool(1);
		running = false;
		this.solver = ExpectMinMax.getInstance();
	}

	/**
	 * @return The Singleton instance of this class.
	 */
	public static GridSolver getInstance() {
		return instance;
	}

	/**
	 * Starts the scheduler.
	 */
	public void start(Grid grid) {
		original = grid;
		if (!isRunning()) {
			logger.info(CLASSNAME, "Starting solver...");
			future = timer.scheduleWithFixedDelay(this, 0, delay,
					TimeUnit.MILLISECONDS);
			running = true;
		}
	}

	/**
	 * Stops the scheduler.
	 */
	public void stop() {
		if (isRunning()) {
			future.cancel(true);
			running = false;
			logger.info(CLASSNAME, "Solver stopped.");
		}
	}

	/**
	 * @return True iff this solver is running, false otherwise.
	 */
	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		if (original.getPossibleMoves() == 0) {
			logger.info(CLASSNAME, "Solver cannot make any more moves.");
			stop();
		} else {
			Direction direction = solver.findMove(original, depth);
			logger.debug(CLASSNAME, "Direction selected: " + direction);
			if (direction != null) {
				original.move(direction);
			}
		}
	}

	/**
	 * Sets the Grid to calculate for and make a move on.
	 * @param grid The grid that should be solved.
	 */
	public void setGrid(Grid grid) {
		if (original == null || !original.toString().equals(grid.toString())) {
			this.original = grid;
		}
	}

	/**
	 * Sets the recursion depth. This may be ignored, depending on the
	 * implementation of the solver being used.
	 * @param depth The new recursion depth.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Sets the delay between consecutive calls to the solver.
	 * @param delay The new delay in milliseconds.
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 * Setter for testing purposes only.
	 */
//	public void setTestObjects(Logger logger, Strategy strategy, Grid grid) {
//		GridSolver.logger = logger;
//		//this.strategy = strategy;
//		this.original = grid;
//	}
}
