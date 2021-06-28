package com.codetest.toyrobot.translator;

import com.codetest.toyrobot.model.Facing;
import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.RequestRobot;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RobotTranslatorTest {
    private final RobotTranslator robotTranslator = new RobotTranslator();

    @Test
    void translatorCorrectlyTranslatesRequestObject() {
        RequestRobot requestRobot = new RequestRobot();
        requestRobot.setFacing(Facing.SOUTH);
        requestRobot.setX(3);
        requestRobot.setY(1);

        Robot robot = robotTranslator.translateRobot(requestRobot);

        assertThat(robot.getFacing(), is(requestRobot.getFacing()));
        assertThat(robot.getX(), is(requestRobot.getX()));
        assertThat(robot.getY(), is(requestRobot.getY()));
    }

    @Test
    void translatorCorrectlyTranslatesRequestObjectWithDifferentValues() {
        RequestRobot requestRobot = new RequestRobot();
        requestRobot.setFacing(Facing.WEST);
        requestRobot.setX(0);
        requestRobot.setY(5);

        Robot robot = robotTranslator.translateRobot(requestRobot);

        assertThat(robot.getFacing(), is(requestRobot.getFacing()));
        assertThat(robot.getX(), is(requestRobot.getX()));
        assertThat(robot.getY(), is(requestRobot.getY()));
    }
}