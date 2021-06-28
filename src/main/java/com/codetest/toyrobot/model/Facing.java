package com.codetest.toyrobot.model;

public enum Facing {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public Facing turnLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            default:
                return this;
        }
    }

    public Facing turnRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            default:
                return this;
        }
    }
}
