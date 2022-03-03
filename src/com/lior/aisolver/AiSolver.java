package com.lior.aisolver;

import com.lior.handlers.FileHendler;
import com.lior.Direction;
import com.lior.Logger;
import com.lior.gameobjects.Grid;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


public class AiSolver {

    /**
     * The scheduler and its future tasks used to make moves with fixed delay.
     */
    private ScheduledExecutorService timer;
    private ScheduledFuture<?> future;
    private String filename;
    private static Logger logger = Logger.getInstance();
    /**
     * The delay between consecutive task executions.
     */
    private int delay = 300;

    /**
     * The original Grid to calculate for and make a move on.
     */
    private Grid original;
    /**
     * The recursion depth to use.
     */
    private int depth = 4;

    /**
     * An indication on whether this solver is running.
     */
    private boolean running;

    private ExpectMinMax expectMinMax;
    private boolean updateBoard;
    private FileHendler fileHendler;

    public AiSolver(){

        expectMinMax = new ExpectMinMax();

        //board = new Board();
        original = new Grid(false);

        fileHendler = new FileHendler();


    }
    /**
     * Starts the scheduler.
     */



    public void playAI() {

    }
    public void startAI(int i){
        int empty = 0;
        boolean canMove = true;
        DateFormat date = new SimpleDateFormat("hh:mm");
        filename = String.format("lior %d %s.txt",i,date.format(new Date()));
        //fileHendler.createFile(filename);
        while (original.getPossibleMoves() != 0){
            Direction direction = expectMinMax.findMove(original, depth);
            System.out.println("Direction selected: " + direction);
            System.out.println("Score is: " + original.getScore());
            if (direction != null) {
                original.move(direction);
            }else{
                break;
            }
//            if(original.getCurrentHighestTile() == 11){
//                //original.repaint();
//                System.out.println("Game over");
//                break;
//            }
            System.out.println(original);
        }
        try {
            fileHendler.write(filename,String.format("highest tile : %d , score : %d",(int)Math.pow(2,original.getCurrentHighestTile()),original.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the scheduler.
     */

    public boolean isRunning() {
        return running;
    }

}
