package com.lior;

import com.lior.aisolver.AiSolver;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //ai.playAI();
        StopWatch s = new StopWatch();
        s.start();
        for (int i = 0; i < 100; i++) {
            AiSolver ai = new AiSolver();
            ai.startAI(i+1);
        }
        s.stop();
        System.out.println("elapsed time = "+ s.getElapsedTimeSecs());
    }
}
