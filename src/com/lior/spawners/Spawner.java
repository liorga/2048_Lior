package com.lior.spawners;

import com.lior.gameobjects.Grid;
import com.lior.gameobjects.Tile;

public interface Spawner {
    /**
     * Finds a location to place a new tile in. It also sets the correct value
     * (correct per implementation, i.e. an implementation that randomly chooses
     * a location will also set a random value).
     *
     * @param grid
     *            The grid to place the new tile in.
     * @return A tile with its index and value, as decided per the
     *         implementation.
     */
    public Tile findTile(Grid grid);
}