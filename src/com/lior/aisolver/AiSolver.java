package com.lior.aisolver;

import com.lior.handlers.FileHendler;
import com.lior.gameobjects.Direction;
import com.lior.gameobjects.Grid;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AiSolver {

    private String filename;
    private Grid original;
    private int depth = 4;
    private ExpectMinMax expectMinMax;
    private FileHendler fileHendler;

    public AiSolver(){
        expectMinMax = new ExpectMinMax();
        original = new Grid(false);
        fileHendler = new FileHendler();
    }
    /**
     * used for run of the game for calculations of the project
     * in the end of the game saving the results to a file with highest tile and score
     */
    public void startAI(int i){
        DateFormat date = new SimpleDateFormat("hh:mm");
        filename = String.format("lior %d %s.txt",i,date.format(new Date()));
        while (original.getPossibleMoves() != 0){
            Direction direction = expectMinMax.findMove(original, depth);
            System.out.println("Direction selected: " + direction);
            System.out.println("Score is: " + original.getScore());
            if (direction != null) {
                original.move(direction);
            }else{
                break;
            }
            System.out.println(original);
        }
        try {
            fileHendler.write(filename,String.format("highest tile : %d , score : %d",(int)Math.pow(2,original.getCurrentHighestTile()),original.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}