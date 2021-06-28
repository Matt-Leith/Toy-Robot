package com.codetest.toyrobot.service;

import com.codetest.toyrobot.model.Board;
import com.codetest.toyrobot.model.Facing;
import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.MoveCommand;
import com.codetest.toyrobot.model.request.RequestRobot;
import com.codetest.toyrobot.translator.RobotTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotService {
    public static final int MINIMUM_BOARD_BOUND_X = 0;
    public static final int MINIMUM_BOARD_BOUND_Y = 0;

    private final RobotTranslator robotTranslator;

    private final List<Robot> robots = new ArrayList<>();
    private final Board board;

    @Autowired
    public RobotService(RobotTranslator robotTranslator) {
        this.robotTranslator = robotTranslator;
        this.board = new Board(5, 5, MINIMUM_BOARD_BOUND_X, MINIMUM_BOARD_BOUND_Y);
    }

    public Robot createRobot(RequestRobot requestRobot) {
        Robot robot = robotTranslator.translateRobot(requestRobot);
        validateCreateRobot(robot);
        robot.setId(robots.size());
        robots.add(robot);
        return robot;
    }

    public Robot getRobot(int robotId) {
        return retrieveRobot(robotId);
    }

    public Robot moveRobot(int robotId, MoveCommand moveCommand) {
        Robot robot = retrieveRobot(robotId);
        validateMoveCommand(moveCommand);
        switch (moveCommand.getCommand()) {
            case MOVE:
                robot.move(board);
                break;
            case LEFT:
                robot.moveFacingLeft();
                break;
            case RIGHT:
                robot.moveFacingRight();
                break;
        }
        return robot;
    }

    private void validateMoveCommand(MoveCommand moveCommand) {
        if (moveCommand == null || moveCommand.getCommand() == null) {
            throw new IllegalArgumentException("Command is a mandatory value and must not be null");
        }
    }

    private void validateCreateRobot(Robot robot) {
        validateRobotPositionIsWithinBoardBounds(robot);
        validateFacing(robot.getFacing());
    }

    private void validateRobotPositionIsWithinBoardBounds(Robot robot) {
        if (robot.getX() > board.getXMaxSize() || robot.getX() < board.getXMinSize()) {
            throw new IllegalArgumentException(String.format("Robot must be placed within the x axis table bounds, %s to %s. Provided robot x position: %s",
                    board.getXMinSize(), board.getXMaxSize(), robot.getX()));
        }
        if (robot.getY() > board.getYMaxSize() || robot.getY() < board.getYMinSize()) {
            throw new IllegalArgumentException(String.format("Robot must be placed within the y axis table bounds, %s to %s. Provided robot y position: %s",
                    board.getYMinSize(), board.getYMaxSize(), robot.getY()));
        }
    }

    private void validateFacing(Facing facing) {
        if (facing == null) {
            throw new IllegalArgumentException("Facing is a mandatory value and must not be null");
        }
    }

    private Robot retrieveRobot(int robotId) {
        try {
            return robots.get(robotId);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new IllegalArgumentException(String.format("Robot does not exist with id %s", robotId));
        }
    }
}
