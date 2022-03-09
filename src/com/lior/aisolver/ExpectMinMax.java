package com.lior.aisolver;

import com.lior.gameobjects.Direction;
import com.lior.gameobjects.Grid;
import com.lior.gameobjects.Tile;

public class ExpectMinMax implements Solver{

    private static final int[] WEIGHTMATRIX = {
            17, 15, 13, 11,
            15, 9, 10, 11,
            13, 10, 8, 8,
            11, 11, 8, 8
    };

    /**
     * array to optimize the calculations with changing depth
     */
    private static final int DEPTHARRAY[] = { 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4,
            4, 4, 4, 4 };
    /**
     * used for checking the influence of depth on the results
     */
//    private static final int DEPTHMAP[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
//            2, 2, 2, 2 };

    /**
     * arrays that holding all the possible values that will spawn in one, with
     * their respective possibilities in the second.
     */
    private static final int NEWTILES[] = { 1, 2 };
    private static final int POSSIBILITIES[] = { 9, 1 };

    /**
     *  array used to give weights to every tile value
     */
    private int[] valueWeight;

    //singleton
    private static Solver instance = new ExpectMinMax();
    public static Solver getInstance() {
        return instance;
    }

    /** Constructs a new Expectimax object. */
    public ExpectMinMax() {
        this.valueWeight = new int[Grid.NTILES];
        initValueWeights();
    }

    /**
     * Initializes the valueWeight array
     */
    private void initValueWeights() {
        int curWeight = 1;
        for (int i = 1; i < valueWeight.length; i++) {
            valueWeight[i] = curWeight;
            curWeight *= 3;
        }
    }

    /**
     * the root of the algorithm tree calculation
     */
    @Override
    public Direction findMove(Grid grid, int depth) {
        double bestValue = 0;
        Direction bestDirection = null;

        for (Direction dir : Direction.values()) {
            Grid clone = grid.clone();
            if (clone.move(dir) == false) {
                continue;
            }

            int empty = clone.getEmptyTiles();
            double value = computerMove(clone, DEPTHARRAY[empty], bestValue);
            if (value >= bestValue) { // FIXME: >= versus >
                bestValue = value;
                bestDirection = dir;
            }
        }
        return bestDirection;
    }

    /**
     * makes evaluation of the grid
     * by multiplying the tile's position weight with
     * its value weight.
     */
    private int evaluate(Tile[] tiles) {
        int value = 0;
        for (int i = 0; i < tiles.length; i++) {
            value += WEIGHTMATRIX[i] * valueWeight[tiles[i].getValue()];
        }
        return value;
    }

    /**
     * The max part of the algorithm. Performs a "player move" and calculates
     * the output's value.
     */
    private double playerMove(Grid grid, int depth, double max) {
        int estimate = evaluate(grid.getTiles());

        if (estimate < 0.7 * max) {
            depth--;
        }

        if (depth <= 0) {
            return estimate;
        }

        int nextDepth = depth - 1;

        if (depth > 3) {
            int empty = grid.getEmptyTiles();
            if (nextDepth > DEPTHARRAY[empty]) {
                nextDepth--;
            }
        }

        double bestValue = 0;
        for (Direction dir : Direction.values()) {
            Grid clone = grid.clone();
            if (clone.move(dir) == false) {
                continue;
            }

            double value = computerMove(clone, nextDepth, bestValue);

            if (value > bestValue) { // FIXME: >= versus >
                bestValue = value;
            }
        }
        return bestValue;
    }

    /**
     * The expect part of the algorithm. Performs a "computer move" and
     * calculates the output's value. A computer move is simply the random
     * placement of the new tiles.
     */
    private double computerMove(Grid grid, int depth, double bestValue) {
        int weight = 0;
        double score = 0;

        for (Tile tile : grid.getTiles()) {
            if (!tile.isEmpty()) {
                continue;
            }

            for (int i = 0; i < POSSIBILITIES.length; i++) {
                tile.setValue(NEWTILES[i]);
                score += POSSIBILITIES[i]
                        * playerMove(grid, depth - 1, bestValue);
                weight += POSSIBILITIES[i];
                tile.setValue(0);
            }
        }
        return weight == 0 ? 0 : score / weight;
    }
}
