package com.lior;

import com.lior.gameobjects.Grid;

public interface Move {
    public Direction findMove(Grid grid, int depth);
}
