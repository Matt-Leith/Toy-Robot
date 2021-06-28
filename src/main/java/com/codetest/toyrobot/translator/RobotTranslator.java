package com.codetest.toyrobot.translator;

import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.RequestRobot;
import org.springframework.stereotype.Service;

@Service
public class RobotTranslator {
    public Robot translateRobot(RequestRobot requestRobot) {
        Robot robot = new Robot();
        robot.setFacing(requestRobot.getFacing());
        robot.setX(requestRobot.getX());
        robot.setY(requestRobot.getY());
        return robot;
    }
}
