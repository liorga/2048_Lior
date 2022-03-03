package com.lior;

import com.lior.aisolver.AiSolver;

/**
 * main class for the test and running of the program to see differences between the AI perms
 */
public class Main {

    public static void main(String[] args) {
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
