package com.lior;

import com.lior.gameobjects.Board;
import com.lior.gameobjects.Grid;
import com.lior.gameobjects.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Game extends JFrame {

//    private static final int SIZE = 4;
//    private Grid board;
//    private JLabel score = new JLabel("Score");
//    private int value = 0;
//    private boolean updateBoard;
//
    public enum Direction {
        UP,
        RIGHT,
        LEFT,
        DOWN
    }
//
//    /**
//     * sets the game gui frame and the board
//     */
//    public Game(String name) {
//        super(name);
//        this.addKeyListener(this);
//        Container gf = this.getContentPane();
//        gf.setLayout(new BoxLayout(gf, BoxLayout.Y_AXIS));
//        gf.setPreferredSize(new Dimension(600, 600));
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        board = new Grid(false);
//        board.setSize(400, 400);
//        board.setLayout(new GridLayout(4, 4));
//        board.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//        Tile[][] tiles = monoToBidi(board.getTiles(),4,4);
//
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                board.add(tiles[i][j]);
//            }
//        }
//        //board.addRandom(2);
//        this.add(score);
//        this.add(board);
//
//
//    }
//
//    public void play() {
//        this.setVisible(true);
//        this.pack();
//    }
//
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    /**
//     * handle the user keys pressed events and make the move according to the key
//     */
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if (board.getPossibleMoves() == 0) {
//            System.out.println("GAME OVER");
//            JOptionPane.showMessageDialog(null, "GAME IS OVER");
//            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//        }
//        switch (key) {
//            case KeyEvent.VK_LEFT:
//                System.out.println("left");
//                board.move(Direction.LEFT);
//                score.setText("Score : " + String.valueOf(board.getScore()));
//                board.repaint();
//                break;
//            case KeyEvent.VK_RIGHT:
//                System.out.println("right");
//                board.move(Direction.RIGHT);
//                score.setText("Score : " + String.valueOf(board.getScore()));
//                board.repaint();
//                break;
//            case KeyEvent.VK_UP:
//                System.out.println("up");
//                board.move(Direction.UP);
//                score.setText("Score : " + String.valueOf(board.getScore()));
//                board.repaint();
//                break;
//            case KeyEvent.VK_DOWN:
//                System.out.println("down");
//                board.move(Direction.DOWN);
//                score.setText("Score : " + String.valueOf(board.getScore()));
//                board.repaint();
//                break;
//            default:
//                break;
//        }
//
////        switch (key){
////            case KeyEvent.VK_LEFT:
////                System.out.println("left");
////                if(board.checkMove(Direction.LEFT)){
////                    updateBoard = true;
////                    score.setText("Score : " + String.valueOf(board.getScore()));
////                    board.repaint();
////                }else{
////                    updateBoard = false;
////                }
////                break;
////            case KeyEvent.VK_RIGHT:
////                System.out.println("right");
////                if(board.checkMove(Direction.RIGHT)){
////                    updateBoard = true;
////                    board.moveRight();
////                    score.setText("Score : " + String.valueOf(board.getScore()));
////                    board.repaint();
////                }else{
////                    updateBoard = false;
////                }
////                break;
////            case KeyEvent.VK_UP:
////                System.out.println("up");
////                if(board.checkMove(Direction.UP)){
////                    updateBoard = true;
////                    board.moveUp();
////                    score.setText("Score : " + String.valueOf(board.getScore()));
////                    board.repaint();
////                }else{
////                    updateBoard = false;
////                }
////                break;
////            case KeyEvent.VK_DOWN:
////                System.out.println("down");
////                if(board.checkMove(Direction.DOWN)){
////                    updateBoard = true;
////                    board.moveDown();
////                    score.setText("Score : " + String.valueOf(board.getScore()));
////                    board.repaint();
////                }else{
////                    updateBoard = false;
////                }
////                break;
////            default:
////                break;
////        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
////        if (updateBoard) {
////            board.addRandom(1);
//            board.repaint();
////        }
//    }
//    public Tile[][] monoToBidi( final Tile[] array, final int rows, final int cols ) {
//        if (array.length != (rows*cols))
//            throw new IllegalArgumentException("Invalid array length");
//
//        Tile[][] bidi = new Tile[rows][cols];
//        for ( int i = 0; i < rows; i++ )
//            System.arraycopy(array, (i*cols), bidi[i], 0, cols);
//
//        return bidi;
//    }
}
