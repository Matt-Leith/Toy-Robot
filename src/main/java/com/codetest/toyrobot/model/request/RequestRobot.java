package com.codetest.toyrobot.model.request;

import com.codetest.toyrobot.model.Facing;

public class RequestRobot {
    private int x;
    private int y;
    private Facing facing;

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
}
