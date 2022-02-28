package com.lior;

import com.lior.gameobjects.Grid;

public interface Move {
    public Game.Direction findMove(Grid grid, int depth);
}
