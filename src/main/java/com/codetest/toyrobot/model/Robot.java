package com.codetest.toyrobot.model;

public class Robot {
    private int id;
    private int x;
    private int y;
    private Facing facing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public void move(Board board) {
        switch (facing) {
            case NORTH:
                if (y >= board.getYMaxSize()) {
                    throw new IllegalArgumentException(String.format("Robot must be moved within the y axis table bounds, %s to %s. Attempted robot y position: %s",
                            board.getYMinSize(), board.getYMaxSize(), y + 1));
                }
                y++;
                break;
            case SOUTH:
                if (y <= board.getYMinSize()) {
                    throw new IllegalArgumentException(String.format("Robot must be moved within the y axis table bounds, %s to %s. Attempted robot y position: %s",
                            board.getYMinSize(), board.getYMaxSize(), y - 1));
                }
                y--;
                break;
            case EAST:
                if (x >= board.getXMaxSize()) {
                    throw new IllegalArgumentException(String.format("Robot must be moved within the x axis table bounds, %s to %s. Attempted robot x position: %s",
                            board.getXMinSize(), board.getXMaxSize(), x + 1));
                }
                x++;
                break;
            case WEST:
                if (x <= board.getXMinSize()) {
                    throw new IllegalArgumentException(String.format("Robot must be moved within the x axis table bounds, %s to %s. Attempted robot x position: %s",
                            board.getXMinSize(), board.getXMaxSize(), x - 1));
                }
                x--;
                break;
        }
    }

    public void moveFacingLeft() {
        facing = facing.turnLeft();
    }

    public void moveFacingRight() {
        facing = facing.turnRight();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
