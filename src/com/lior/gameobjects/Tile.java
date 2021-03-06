package com.lior.gameobjects;

import java.util.Observable;

public class Tile extends Observable {


    /** The power of two that makes the value (e.g. 2^1, 2^2, 2^3, 2^4, ...). */
    private int value;

    /** The index into the Grid array. */
    private int index;
    /** The index of the tile to move to. */
    private int destIndex;

    /** Indicates whether this Tile has been merged in the current move. */
    private boolean isMerged;

    /** Indicates whether a Tile should spawn, move or merge. */
    private boolean spawning, moving, merging;

    /**
     * Creates a new Tile with the given value.
     *
     * @param index
     *            The index into the Grid array.
     * @param value
     *            The value of the Tile.
     */
    public Tile(int index, int value) {
        this.index = index;
        setValue(value);
        setMerged(false);
    }

    /**
     * @return The value of the Tile.
     * */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value of the Tile and notifies its observers.
     *
     * @param value
     *            The new value.
     */
    public void setValue(int value) {
        this.value = value;
        changed();
    }


    public int getIndex() {
        return this.index;
    }


    public int getDestination() {
        return destIndex;
    }

    /**
     * Sets the index of the Tile into the Grid array and updates the x and y
     * coordinates accordingly.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isEmpty() {
        return this.value == 0;
    }


    public boolean isMerged() {
        return this.isMerged;
    }

    /**
     * Sets the merged state of this Tile.
     */
    public void setMerged(boolean isMerged) {
        this.isMerged = isMerged;
    }

    /** Resets the Tile to its default state. */
    public void reset() {
        setValue(0);
        setMerged(false);
        spawning = moving = merging = false;
        changed();
    }

    /** Doubles the value of the Tile. */
    public void incValue() {
        setValue(++value);
    }

    /** Sets spawning to true and notifies the observers. */
    public void spawn() {
        spawning = true;
        changed();
        spawning = false;
    }

    /** Sets merging to true and notifies the observers. */
    public void merge() {
        merging = true;
        changed();
        merging = false;
    }

    /** Sets moving to true and notifies the observers. */
    public void move(int destIndex) {
        moving = true;
        this.destIndex = destIndex;
        changed();
        index = destIndex;
        moving = false;
    }


    public boolean isMerging() {
        return merging;
    }


    public boolean isMove() {
        return moving;
    }

    public boolean isSpawn() {
        return spawning;
    }

    /** Marks the observable as changed and notifies the observers. */
    public void changed() {
        if (!hasChanged()) {
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
