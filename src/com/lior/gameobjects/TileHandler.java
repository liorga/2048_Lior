package com.lior.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



/**
 * This class is responsible for moving tiles on the grid.
 */
public class TileHandler {
    /** The length of a row on the grid. */
    private static final int ROW_LENGTH = 4;

    /** The array holding all the tiles. */
    private Tile[] tiles;

    /** The reference to the current grid. */
    private Grid grid;

    /** Indicates whether a move has been made. */
    private boolean isMoveMade;

    /** The points to be awarded to the player */
    private int scoreInc;

    /**
     * Constructs a new TileHandler object.
     *

     *            The array holding the tiles.
     */
    public TileHandler(Grid grid) {
        this.grid = grid;
        this.tiles = grid.getTiles();
        this.scoreInc = 0;
        this.isMoveMade = false;
    }

    public void moveLeft() {
        TileIterator iterator = null;
        /* For each row in the grid, check if moves can be made. */
        for (int index = 0; index < tiles.length; index += ROW_LENGTH) {
            for (int i = 0; i < ROW_LENGTH; i++) {
                iterator = new TileIterator(Arrays.copyOfRange(tiles, index,
                        index + ROW_LENGTH));
                move(iterator);
            }
            resetMerged(iterator);
        }
        grid.setTiles(tiles);
    }


    public void moveRight() {
        tiles = rotate(180);
        moveLeft();
        tiles = rotate(180);
        grid.setTiles(tiles);
    }


    public void moveDown() {
        tiles = rotate(90);
        moveLeft();
        tiles = rotate(270);
        grid.setTiles(tiles);
    }


    public void moveUp() {
        tiles = rotate(270);
        moveLeft();
        tiles = rotate(90);
        grid.setTiles(tiles);
    }

    /**
     * The method that actually performs the move.
     */
    private void move(TileIterator iterator) {
        Tile collidee = null;
        Tile collider = iterator.next();
        while (iterator.hasNext()) {
            collidee = collider;
            collider = iterator.next();
            if (collider.getValue() == 0) {
                continue;
            } else {
                if (collidee.isEmpty()) {
                    handleMove(collider, collidee);
                } else if (!collidee.isMerged() && !collider.isMerged()
                        && collider.getValue() == collidee.getValue()) {
                    handleMerge(collider);
                    handleMove(collider, collidee);
                }
            }
        }
        iterator.reset();
    }

    /**
     * Handles the move and updating the indexes
     */
    private void handleMove(Tile collider, Tile collidee) {
        isMoveMade = true;
        List<Tile> tileList = Arrays.asList(tiles);
        int originalIndex = collider.getIndex();
        collider.move(collidee.getIndex());
        collidee.setIndex(originalIndex);
        collidee.reset();
        Collections.swap(tileList, tileList.indexOf(collider),
                tileList.indexOf(collidee));
    }

    /**
     * Updates the variables of the the colliding Tile after a merge.
     */
    private void handleMerge(Tile collider) {
        collider.incValue();
        collider.setMerged(true);
        collider.merge();
        scoreInc += Math.pow(2, collider.getValue());
    }

    /**
     * Rotates the grid to an angle that we went
     */
    private Tile[] rotate(int angle) {
        Tile[] res = new Tile[16];

        int offsetX = 3, offsetY = 3;
        if (angle == 90) {
            offsetY = 0;
        } else if (angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                res[newX + newY * 4] = tiles[x + y * 4];
            }
        }
        return res;
    }

    /**
     * Resets all merged tiles in a row or column so they can merge again.
     */
    private void resetMerged(TileIterator iterator) {
        while (iterator.hasNext()) {
            iterator.next().setMerged(false);
        }
        iterator.reset();
    }

    /**
     * @return True if a move has been made, false otherwise.
     */
    public boolean isMoveMade() {
        return this.isMoveMade;
    }

    /**
     * @return The value to be incremented.
     */
    public int getScoreInc() {
        return this.scoreInc;
    }

    /**
     * Prepares for the next round of moving tiles.
     */
    public void reset() {
        this.scoreInc = 0;
        this.isMoveMade = false;
    }
}
