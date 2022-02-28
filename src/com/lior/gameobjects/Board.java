package com.lior.gameobjects;

import com.lior.Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {

    private static final int SIZE = 4;
    public final int TWO_PROBABILITY = 90;
    private Random random;
    private Tile[][] tiles =  new Tile[SIZE][SIZE];
    private int score;


    public Board(){
        this.random = new Random();
        initBoard();
    }

    public void initBoard(){
        for (int i = 0 ; i < SIZE ; i++){
            for (int j = 0; j < SIZE; j++) {
                //tiles[i][j] = new Tile();
            }
        }
    }


    public void moveUp(){
        rotateTilesRight();
        slideRight();
        mergeTilesRight();
        slideRight();
        rotateTilesLeft();
    }

    public void moveDown(){
        rotateTilesRight();
        slideLeft();
        mergeTilesLeft();
        slideLeft();
        rotateTilesLeft();
    }

    public void moveRight(){
        slideRight();
        mergeTilesRight();
        slideRight();
    }

    public void moveLeft(){
        slideLeft();
        mergeTilesLeft();
        slideLeft();
    }

    // TODO: 18/11/2021  change the queue to something more efficient
    public void slideRight(){
        for (int i = 0; i < SIZE; i++) {
            Deque<Integer> row = new LinkedList<>();
            for (int j = 0; j < SIZE; j++) {
                if(tiles[i][j].getValue() != 0){
                    row.add(tiles[i][j].getValue());
                }
            }
            //how many numbers to add to the queue
            int zeroes = SIZE - row.size();
            for (int j = 0; j < zeroes; j++) {
                row.addFirst(0);
            }

            for (int j = 0; j < SIZE; j++) {
                tiles[i][j].setValue(row.pollFirst());
            }

        }

    }

    /**
     * slide all the tiles to the left and if there is two tiles with the same value
     * marge the together
     */
    public void slideLeft(){

        for (int i = 0; i < SIZE; i++) {
            Deque<Integer> row = new LinkedList<>();
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].getValue() != 0) {
                    row.add(tiles[i][j].getValue());
                }
            }
            //how many numbers to add to the queue
            int zeroes = SIZE - row.size();
            for (int j = 0; j < zeroes; j++) {
                row.addLast(0);
            }

            for (int j = 0; j < SIZE; j++) {
                tiles[i][j].setValue(row.pollFirst());
            }
        }
    }

    public void rotateTilesLeft(){
        Tile[][] newTiles = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newTiles[(SIZE-1)-j][i] = tiles[i][j];
            }
        }


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = newTiles[i][j];
            }
        }

    }

    //todo check in place rotation
    public void rotateTilesRight(){
        Tile[][] newTiles = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newTiles[j][(SIZE-1)-i] = tiles[i][j];
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = newTiles[i][j];
            }
        }
    }

    public void mergeTilesLeft(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if(tiles[i][j].getValue() == tiles[i][j+1].getValue()){
                    tiles[i][j+1].setValue( (tiles[i][j].getValue() + tiles[i][j+1].getValue()));
                    setScore(tiles[i][j+1].getValue());
                    //tiles[i][j].clear();
                }
            }
        }
    }

    public void mergeTilesRight(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 1; j > 0; j--) {
                if(tiles[i][j].getValue() == tiles[i][j-1].getValue()){
                    tiles[i][j-1].setValue( (tiles[i][j].getValue() + tiles[i][j-1].getValue()));
                    setScore(tiles[i][j-1].getValue());
                    //tiles[i][j].clear();
                }
            }
        }
    }

    public boolean checkMove(Game.Direction direction){
        boolean res = false;
        switch (direction){
            case UP:
                rotateTilesRight();
                res = checkRight();
                rotateTilesLeft();
                break;
            case DOWN:
                rotateTilesRight();
                res = checkLeft();
                rotateTilesLeft();
                break;
            case RIGHT:
                res = checkRight();
                break;
            case LEFT:
                res = checkLeft();
                break;
            default:
                break;
        }
        System.out.println(res);
        return res;
    }
    //return true if can make left move
    public boolean checkLeft(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if(this.tiles[i][j].getValue() == 0 && this.tiles[i][j+1].getValue() != 0) {
                    return true;
                }
                if(this.tiles[i][j].getValue() != 0 && this.tiles[i][j].getValue() == this.tiles[i][j+1].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
    //return true if can make right move
    public boolean checkRight(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 1; j > 0; j--) {
                if(this.tiles[i][j].getValue() == 0 && this.tiles[i][j-1].getValue() != 0) {
                    return true;
                }
                if(this.tiles[i][j].getValue() != 0 && this.tiles[i][j].getValue() == this.tiles[i][j-1].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * adding random new tile 90% chance the new tile will be 2
     * and 10% chance the new tile will be 4
     */


    public void addRandom(int n) {
        ArrayList<Point> available = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].getValue() == 0) {
                    available.add(new Point(i, j));
                }
            }
        }

        Random r = new Random();
        if (available.isEmpty()) {
//            System.out.println("Game Over");
//            // TODO: 24/12/2021 add game over message in gui
            return;
        }
        // n == number of tiles to randomly put
        for (int i = 0; i < n; i++) {
            Point p = available.get(r.nextInt(available.size()));
            // weight the 2 so it shows up more...not really a 10% chance
            tiles[(int)p.getX()][(int)p.getY()].setValue(r.nextInt(4) == 1 ? 4 : 2);
            available.remove(p);
        }
    }

    /**
     *
     * @return true if board is full
     */
    public boolean isGameOver(){
        ArrayList<Point> available = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].getValue() == 0) {
                    available.add(new Point(i, j));
                }
            }
        }
        if (available.isEmpty()) {
            return checkMove(Game.Direction.UP) || checkMove(Game.Direction.DOWN) || checkMove(Game.Direction.LEFT) || checkMove(Game.Direction.RIGHT);
        }
        return true;
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }
}
