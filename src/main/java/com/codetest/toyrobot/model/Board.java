package com.codetest.toyrobot.model;

public class Board {
    private final int xMaxSize;
    private final int yMaxSize;
    private final int xMinSize;
    private final int yMinSize;

    public Board(int xMaxSize, int yMaxSize, int xMinSize, int yMinSize) {
        this.xMaxSize = xMaxSize - 1;
        this.yMaxSize = yMaxSize - 1;
        this.xMinSize = xMinSize;
        this.yMinSize = yMinSize;
    }

    public int getXMaxSize() {
        return xMaxSize;
    }

    public int getYMaxSize() {
        return yMaxSize;
    }

    public int getXMinSize() {
        return xMinSize;
    }

    public int getYMinSize() {
        return yMinSize;
    }
}
