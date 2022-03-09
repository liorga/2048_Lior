package com.lior.gameobjects;

import java.util.Iterator;

/**
 * The TileIterator class is used to iterate over all the tiles in the Grid,
 * without having to care about the internal representation.
 */
public class TileIterator implements Iterator<Tile> {

    private Tile[] tiles;

    private int index;

    public TileIterator(Tile[] tiles) {
        this.tiles = tiles;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < tiles.length;
    }

    @Override
    public Tile next() {
        return tiles[index++];
    }

    /**
     * Resets the position to zero, so the iterator can be used again
     */
    public void reset() {
        this.index = 0;
    }

    /**
     * @return The current index of the iterator.
     */
    public int getIndex() {
        return this.index;
    }

    @Override
    public void remove() {
    }
}
