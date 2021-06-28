package com.codetest.toyrobot.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FacingTest {
    @Test
    void facingTurnsLeftCorrectly() {
        Facing facing = Facing.NORTH;
        facing = facing.turnLeft();
        assertThat(facing, is(Facing.WEST));
        facing = facing.turnLeft();
        assertThat(facing, is(Facing.SOUTH));
        facing = facing.turnLeft();
        assertThat(facing, is(Facing.EAST));
        facing = facing.turnLeft();
        assertThat(facing, is(Facing.NORTH));
    }

    @Test
    void facingTurnsRightCorrectly() {
        Facing facing = Facing.NORTH;
        facing = facing.turnRight();
        assertThat(facing, is(Facing.EAST));
        facing = facing.turnRight();
        assertThat(facing, is(Facing.SOUTH));
        facing = facing.turnRight();
        assertThat(facing, is(Facing.WEST));
        facing = facing.turnRight();
        assertThat(facing, is(Facing.NORTH));
    }
}