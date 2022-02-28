package com.lior.gameobjects;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class Tile extends Observable {

//    private int value;
//    //optional location on board in this class maybe will be in the board
//
//    public Tile(){
//        this.value = 0;
//        //this.setText(String.valueOf(value));
//        this.setHorizontalAlignment(CENTER);
//        this.setVerticalAlignment(CENTER);
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public void setValue(int value) {
//        this.value = value;
//    }
//
//    public void merge(Tile tile){
//        this.setValue(this.value + tile.getValue());
//    }
//
//    public void clear(){
//        this.setValue(0);
//    }
//
//
//
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

    /**
     * @return The index of the Tile into the Grid array.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return The index of the Tile to move to.
     */
    public int getDestination() {
        return destIndex;
    }

    /**
     * Sets the index of the Tile into the Grid array and updates the x and y
     * coordinates accordingly.
     *
     * @param index
     *            The new index.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return True if the tile is empty (value 0), false otherwise.
     */
    public boolean isEmpty() {
        return this.value == 0;
    }

    /**
     * @return True if this Tile has been merged, false otherwise.
     */
    public boolean isMerged() {
        return this.isMerged;
    }

    /**
     * Sets the merged state of this Tile.
     *
     * @param isMerged
     *            The new merged state.
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
    public void doubleValue() {
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
        /*
         * After notifying the observers, update this Tile's index. This needs
         * to be done after notifying as the destination would otherwise be the
         * same as the current index.
         */
        index = destIndex;
        moving = false;
    }

    /** @return True iff the tile should merge. */
    public boolean shouldMerge() {
        return merging;
    }

    /** @return True iff the tile should move. */
    public boolean shouldMove() {
        return moving;
    }

    /** @return True iff the tile should spawn. */
    public boolean shouldSpawn() {
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

    public Color getBackground()
    {
        //returning the color by the tile value each value get different color
        switch ( value )
        {
            case 1:
                return new Color( 0xeee4da );
            case 2:
                return new Color(0xFFE7DAB6, true);
            case 3:
                return new Color( 0xf2b179 );
            case 4:
                return new Color( 0xf59563 );
            case 5:
                return new Color( 0xf67c5f );
            case 6:
                return new Color( 0xf65e3b );
            case 7:
                return new Color( 0xedcf72 );
            case 8:
                return new Color( 0xedcc61 );
            case 9:
                return new Color( 0xedc850 );
            case 10:
                return new Color( 0xedc53f );
            case 11:
                return new Color( 0xedc22e );
        }

        return new Color( 0xcdc1b4 );
    }

    /**
     * sets the tile gui to be painted with the right colors and values to be draw on the gui board
     */
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (value == 0){
//            g.setColor(getBackground());
//            g.fillRect(32-20,28-20,120,120);
//            return;
//        }
//        else{
//            g.setColor(getBackground());
//            g.fillRect(32-20,28-20,120,120);
//            g.setColor(Color.BLACK);
//
//            g.drawString(String.valueOf((int)Math.pow(2,value)),55,60);
//        }
//    }
}
