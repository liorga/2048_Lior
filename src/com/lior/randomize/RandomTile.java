package com.lior.randomize;

import com.lior.gameobjects.Grid;
import com.lior.gameobjects.Tile;

public class RandomTile implements Maker {
    private static RandomTile instance = new RandomTile();

    /** Overrides the default constructor. */
    private RandomTile() {
    }

    /**
     * @return The singleton instance of the RandomSpawner.
     */
    public static RandomTile getInstance() {
        return instance;
    }

    /**
     * chances of getting a value of 2 are 90%, while the changes of a value of 4 are 10%.
     */
    @Override
    public Tile findTile(Grid grid) {
        Tile[] tiles = grid.getTiles();

        int index = (int) (Math.random() * tiles.length);
        while (!tiles[index].isEmpty() && grid.getPossibleMoves() > 0) {
            index = (int) (Math.random() * tiles.length);
        }

        return new Tile(index, initialValue());
    }

    /**
     * @return A random value, which is either 2 or 4. The chances of getting a
     *         value of 2 are 90%, while the changes of a value of 4 are 10%.
     */
    private int initialValue() {
        return Math.random() < 0.9 ? Grid.TWO : Grid.FOUR;
    }
}
