package com.lior.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.lior.TwentyFourtyGame;
import com.lior.randomize.RandomTile;
import com.lior.randomize.Maker;
import com.lior.states.RunningState;


/**
 * This class represents the 4x4 grid you see when playing 2048.
 * <p>
 * The internal structure is a simple one-dimensional array. Considering we only
 * require simple operations, this is deemed fast enough, while being very
 * simple at the same time.
 * <p>
 * For example, imagine the grid being laid out like this:
 * <p>
 * +---+---+---+---+
 * | 0 | 1 | 2 | 3 |
 * +---+---+---+---+
 * | 4 | 5 | 6 | 7 |
 * +---+---+---+---+
 * | 8 | 9 | 10| 11|
 * +---+---+---+---+
 * | 12| 13| 14| 15|
 * +---+---+---+---+
 * <p>
 * Now, a square on field 10 can move left or right by adding or subtracting 1
 * from its index. It can move up or down by adding or subtracting 4 from its
 * index.
 */
public class Grid extends Observable implements Cloneable {
    
    /**
     * This enumeration is used to indicate with which difficulty a new
     * tile will be spawned.
     */
    public enum Difficulty {
        RANDOM, EASY, MEDIUM, HARD;
    }

    /**
     * The grid contains sixteen tiles.
     */
    public static final int NTILES = 16;

    /**
     * The lowest value to start with.
     */
    public static final int TWO = 1;

    /**
     * The highest value to start with.
     */
    public static final int FOUR = 2;

    /**
     * The tile that will make you win.
     */
    public static final int WIN = 11;

    /** The singleton reference to the Logger instance. */
   // private Logger logger = new Logger(Grid.class);

    /**
     * The name of the instance, initialized to the name of the class. This can
     * be either Grid, LocalGrid or RemoteGrid.
     */
    private String gridName = Grid.class.getSimpleName();

    /**
     * The array containing all sixteen tiles.
     */
    private Tile[] tiles;

    /**
     * The TileIterator is used to iterate over the tiles.
     */
    private TileIterator iterator;

    /**
     * The TileHandler is used to move the tiles.
     */
    private TileHandler tileHandler;

    /**
     * The difficulty with which new tiles will be spawned.
     */
    private Difficulty difficulty;

    /**
     * The current score of the Grid.
     */
    private int score;

    /**
     * The highest tile value present in the Grid.
     */
    private int highestTile;


    /**
     * Creates a new Grid with NTILES Tile objects.
     *
     * @param isEmpty True if the grid should be empty, false otherwise.
     */
    public Grid(boolean isEmpty) {
        this.tiles = new Tile[NTILES];
        this.iterator = new TileIterator(tiles);
        this.tileHandler = new TileHandler(this);

        this.difficulty = Difficulty.RANDOM;

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(i, 0);
        }
        if (!isEmpty) {
            initGrid();
        }
    }

    /**
     * Initializes the grid, creating two tiles with real values of 2 or 4 and
     * leaving the rest empty.
     */
    private void initGrid() {
        Maker randomSpawner = RandomTile.getInstance();

        Tile random = randomSpawner.findTile(this);
        tiles[random.getIndex()].setValue(random.getValue());
        random = randomSpawner.findTile(this);
        tiles[random.getIndex()].setValue(random.getValue());
    }

    /**
     * Sets a Tile's parameters by index.
     *
     * @param index The Tile's index on the grid.
     * @param value The Tile's value (should be a value bigger than zero)).
     */
    public void setTile(int index, int value) {
        tiles[index].setValue(value);
        changed();
        updateHighestTile();
    }

    /**
     * Sets this Grid's tiles to the provided array.
     *
     * @param tiles The tiles to set.
     */
    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
        this.iterator = new TileIterator(tiles);
        updateHighestTile();
        changed();
    }

    /**
     * Resets the grid, by calling reset on all the Tiles it contains,
     * reinitializing itself, clearing the undo/redo stacks and checking for the
     * new highest value. It also sets the game state to running. Finally, it
     * notifies the observers.
     */
    public void restart() {
        //logger.info(gridName, "Restarting grid.");

        while (iterator.hasNext()) {
            iterator.next().reset();
        }
        iterator.reset();
        initGrid();

        score = 0;
        highestTile = 0;
        updateHighestTile();

        TwentyFourtyGame.setState(RunningState.getInstance());
        changed();
    }

    /**
     * This method is the one method used for moving tiles.
     * <p>
     * Its parameter indicates which direction is to be moved in. The actual
     * moving will be delegated to TileHandler. If a move has been made, it
     * updates the score and creates a new Tile.
     *
     * @param direction The direction in which is to be moved.
     * @return true iff a move has been made, false otherwise.
     */
    public boolean move(Direction direction) {
        boolean res = false;

        switch (direction) {
            case LEFT:
                tileHandler.moveLeft();
                break;
            case RIGHT:
                tileHandler.moveRight();
                break;
            case UP:
                tileHandler.moveUp();
                break;
            case DOWN:
                tileHandler.moveDown();
                break;
        }

        if (tileHandler.isMoveMade()) {
            //logger.info(gridName, "Move " + direction + " succesfully made.");
            updateAfterMove();
            res = true;
        } else {
            //logger.debug(gridName, "Move " + direction + " ignored.");
        }

        tileHandler.reset();
        return res;
    }

    /**
     * Updates the grid and its observers iff a move has been made.
     */
    public void updateAfterMove() {
        if (!tileHandler.isMoveMade()) {
            return;
        }
        updateScore();
        spawnNewTile();
        updateHighestTile();
        changed();
    }


    /**
     * Updates the score with the score increment from the TileHandler class.
     */
    private void updateScore() {
        score += tileHandler.getScoreInc();
        //logger.info(gridName, "Score value set to " + score + ".");
    }

    /**
     * Spawns a Tile at a random empty location.
     */
    public void spawnNewTile() {
        Maker spawner;

        spawner = RandomTile.getInstance();

        Tile tile = spawner.findTile(this);
        int location = tile.getIndex();
        int value = tile.getValue();
        setTile(location, value);
        tiles[location].spawn();

        //logger.debug(gridName, "New tile set at location " + location
        //+ " (value = " + value + ").");
    }

    /**
     * Updates the highest Tile value present in the grid.
     */
    public void updateHighestTile() {
        Tile t = null;
        while (iterator.hasNext()) {
            t = iterator.next();
            if (t.getValue() > highestTile) {
                highestTile = t.getValue();
            }
        }
        iterator.reset();
    }

    /**
     * @return The number of empty tiles.
     */
    public int getEmptyTiles() {
        int empty = 0;
        while (iterator.hasNext()) {
            if (iterator.next().isEmpty()) {
                empty++;
            }
        }
        iterator.reset();
        return empty;
    }

    /**
     * @return The amount of possible moves.
     */
    public int getPossibleMoves() {
        TileIterator iterator = new TileIterator(tiles);
        Tile t = null;
        int moves = 0, value = 0;

        while (iterator.hasNext()) {
            t = iterator.next();
            /* An empty Tile cannot move. */
            if (t.isEmpty()) {
                continue;
            }

            value = t.getValue();
            List<Tile> neighbors = getTileNeighbors(t.getIndex());

            /* For all neighboring tiles, compare the values. */
            for (Tile neighbor : neighbors) {
                if (neighbor.getValue() == value || neighbor.isEmpty()) {
                    moves++;
                }
            }
        }
        iterator.reset();

        return moves;
    }

    /**
     * Forms a list of neighboring Tiles by index.
     *
     * @param index The Tile index.
     * @return A list of neighboring tiles.
     */
    public List<Tile> getTileNeighbors(int index) {
        List<Tile> neighbors = new ArrayList<Tile>();

        /*
         * Right neighbor: check if the index we're checking is not the right
         * edge of the grid by making sure (index + 1) is a not a multiple of 4.
         */
        if ((index + 1) % 4 != 0 && index + 1 < tiles.length) {
            neighbors.add(tiles[index + 1]);
        }

        /*
         * Left neighbor: check if the index we're checking is not the left edge
         * of the grid by making sure (index - 1) is a not a multiple of 4.
         */
        if (index % 4 != 0 && index - 1 >= 0) {
            neighbors.add(tiles[index - 1]);
        }

        /*
         * Lower neighbor: check if the index we're checking is not the bottom
         * edge of the grid by making sure (index + 4) is smaller than
         * grid.length:
         */
        if (index + 4 < tiles.length) {
            neighbors.add(tiles[index + 4]);
        }

        /*
         * Upper neighbor: check if the index we're checking is not the top edge
         * of the grid by making sure (index - 4) is not smaller than zero:
         */
        if (index - 4 >= 0) {
            neighbors.add(tiles[index - 4]);
        }

        return neighbors;
    }

    /** Notifies the observers iff the grid has changed. */
    private void changed() {
        if (!hasChanged()) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The highest Tile value.
     */
    public int getCurrentHighestTile() {
        return highestTile;
    }

    /**
     * @return The array containing all the Tiles.
     */
    public Tile[] getTiles() {
        return tiles;
    }

    /**
     * @return The TileHandler object used by the Grid.
     */
    public TileHandler getTileHandler() {
        return tileHandler;
    }

    /**
     * @return The name of this instance, one of Grid, LocalGrid and RemoteGrid.
     */
    public String getGridName() {
        return gridName;
    }

    /**
     * Sets the TileHandler object used by the grid.
     *
     * @param tileHandler The TileHandler object to set.
     */
    public void setTileHandler(TileHandler tileHandler) {
        this.tileHandler = tileHandler;
    }

    /**
     * Sets the difficulty with which new tiles will be spawned.
     *
     * @param difficulty The difficulty with which new tiles will be spawned.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Sets the current score to the value provided.
     *
     * @param score The new score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the name of this instance.
     *
     * @param name The name for this instance.
     */
    public void setGridName(String name) {
        this.gridName = name;
    }

    @Override
    public String toString() {
        String res = "";
        int cnt = 1;
        while (iterator.hasNext()) {

            res += String.valueOf((int) Math.pow(2, iterator.next().getValue())) + "\t";
            if (cnt % 4 == 0) {
                res += "\n";
            }
            cnt++;
        }
        iterator.reset();

        res = res.substring(0, res.length() - 1);
        return res;
    }


    @Override
    public Grid clone() {
        Grid newGrid = new Grid(true);

        for (int i = 0; i < tiles.length; i++) {
            newGrid.tiles[i] = new Tile(i, tiles[i].getValue());
        }
        newGrid.setScore(score);
        newGrid.highestTile = highestTile;
        return newGrid;
    }
}
