package com.codetest.toyrobot.service;

import com.codetest.toyrobot.model.Command;
import com.codetest.toyrobot.model.Facing;
import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.MoveCommand;
import com.codetest.toyrobot.model.request.RequestRobot;
import com.codetest.toyrobot.translator.RobotTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RobotServiceTest {
    @Mock
    private RobotTranslator robotTranslator;

    private RobotService robotService;

    @BeforeEach
    void setUp() {
        robotService = new RobotService(robotTranslator);
    }

    @Test
    void getRobotRetrievesSuccessfully() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.EAST);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        Robot retrievedRobot = robotService.getRobot(createdRobot.getId());

        assertThat(retrievedRobot.getId(), is(createdRobot.getId()));
    }

    @Test
    void getRobotRetrievesSuccessfullyMultipleRobots() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.EAST);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot firstCreatedRobot = robotService.createRobot(requestRobot);
        Robot secondCreatedRobot = robotService.createRobot(requestRobot);

        Robot firstRetrievedRobot = robotService.getRobot(firstCreatedRobot.getId());
        Robot secondRetrievedRobot = robotService.getRobot(secondCreatedRobot.getId());

        assertThat(firstRetrievedRobot.getId(), is(firstCreatedRobot.getId()));
        assertThat(secondRetrievedRobot.getId(), is(secondCreatedRobot.getId()));
    }

    @Test
    void getRobotHandlesRobotNotFoundWithRobotsLoaded() {
        final int robotId = 5;
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.EAST);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        robotService.createRobot(requestRobot);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.getRobot(robotId));
        assertThat(exception.getMessage(), is("Robot does not exist with id 5"));
    }

    @Test
    void getRobotHandlesRobotNotFoundWithNoRobots() {
        final int robotId = 0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.getRobot(robotId));
        assertThat(exception.getMessage(), is("Robot does not exist with id 0"));
    }

    @Test
    void createRobotCreatesMultipleRobots() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.EAST);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot firstCreatedRobot = robotService.createRobot(requestRobot);
        Robot secondCreatedRobot = robotService.createRobot(requestRobot);

        Robot firstRetrievedRobot = robotService.getRobot(firstCreatedRobot.getId());
        Robot secondRetrievedRobot = robotService.getRobot(secondCreatedRobot.getId());

        assertThat(firstRetrievedRobot.getId(), is(firstCreatedRobot.getId()));
        assertThat(secondRetrievedRobot.getId(), is(secondCreatedRobot.getId()));
    }

    @Test
    void createHandlesInputOutOfBoundsXAxisLowerBound() {
        RequestRobot requestRobot = new RequestRobot();

        Robot translatedRobot = new Robot();
        translatedRobot.setX(-1);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(translatedRobot);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(requestRobot));
        assertThat(exception.getMessage(), is("Robot must be placed within the x axis table bounds, 0 to 4. Provided robot x position: -1"));
    }

    @Test
    void createHandlesInputOutOfBoundsXAxisUpperBound() {
        RequestRobot requestRobot = new RequestRobot();

        Robot translatedRobot = new Robot();
        translatedRobot.setX(5);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(translatedRobot);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(requestRobot));
        assertThat(exception.getMessage(), is("Robot must be placed within the x axis table bounds, 0 to 4. Provided robot x position: 5"));
    }

    @Test
    void createHandlesNullFacing() {
        RequestRobot requestRobot = new RequestRobot();

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(new Robot());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(requestRobot));
        assertThat(exception.getMessage(), is("Facing is a mandatory value and must not be null"));
    }

    @Test
    void createHandlesInputOutOfBoundsYAxisLowerBound() {
        RequestRobot requestRobot = new RequestRobot();

        Robot translatedRobot = new Robot();
        translatedRobot.setY(-1);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(translatedRobot);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(requestRobot));
        assertThat(exception.getMessage(), is("Robot must be placed within the y axis table bounds, 0 to 4. Provided robot y position: -1"));
    }

    @Test
    void createHandlesInputOutOfBoundsYAxisUpperBound() {
        RequestRobot requestRobot = new RequestRobot();

        Robot translatedRobot = new Robot();
        translatedRobot.setY(5);
        when(robotTranslator.translateRobot(requestRobot)).thenReturn(translatedRobot);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(requestRobot));
        assertThat(exception.getMessage(), is("Robot must be placed within the y axis table bounds, 0 to 4. Provided robot y position: 5"));
    }

    @Test
    void moveCategorisesInputCorrectlyForMultipleRobots() {
        RequestRobot firstRequestRobot = new RequestRobot();
        firstRequestRobot.setX(1);
        RequestRobot secondRequestRobot = new RequestRobot();
        secondRequestRobot.setX(2);

        Robot firstRobot = new Robot();
        firstRobot.setFacing(Facing.NORTH);
        firstRobot.setX(3);
        firstRobot.setY(2);

        Robot secondRobot = new Robot();
        secondRobot.setFacing(Facing.SOUTH);
        secondRobot.setX(2);
        secondRobot.setY(1);

        when(robotTranslator.translateRobot(firstRequestRobot)).thenReturn(firstRobot);
        when(robotTranslator.translateRobot(secondRequestRobot)).thenReturn(secondRobot);

        Robot firstCreatedRobot = robotService.createRobot(firstRequestRobot);
        Robot secondCreatedRobot = robotService.createRobot(secondRequestRobot);

        MoveCommand firstRobotMoveCommand = new MoveCommand();
        firstRobotMoveCommand.setCommand(Command.MOVE);
        robotService.moveRobot(firstCreatedRobot.getId(), firstRobotMoveCommand);

        assertThat(firstCreatedRobot.getFacing(), is(Facing.NORTH));
        assertThat(firstCreatedRobot.getX(), is(3));
        assertThat(firstCreatedRobot.getY(), is(3));

        MoveCommand secondRobotMoveCommand = new MoveCommand();
        secondRobotMoveCommand.setCommand(Command.LEFT);
        robotService.moveRobot(secondCreatedRobot.getId(), secondRobotMoveCommand);
        assertThat(secondCreatedRobot.getFacing(), is(Facing.EAST));
        assertThat(secondCreatedRobot.getX(), is(2));
        assertThat(secondCreatedRobot.getY(), is(1));
    }

    @Test
    void moveCategorisesInputCorrectlyForMultipleMoveCommands() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.NORTH);
        robot.setX(2);
        robot.setY(2);

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        MoveCommand robotMoveCommand = new MoveCommand();
        robotMoveCommand.setCommand(Command.MOVE);
        MoveCommand robotRightCommand = new MoveCommand();
        robotRightCommand.setCommand(Command.RIGHT);
        MoveCommand robotLeftCommand = new MoveCommand();
        robotLeftCommand.setCommand(Command.LEFT);

        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);
        robotService.moveRobot(createdRobot.getId(), robotRightCommand);
        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);
        robotService.moveRobot(createdRobot.getId(), robotRightCommand);
        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);
        robotService.moveRobot(createdRobot.getId(), robotLeftCommand);
        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);

        assertThat(createdRobot.getFacing(), is(Facing.EAST));
        assertThat(createdRobot.getX(), is(4));
        assertThat(createdRobot.getY(), is(2));
    }

    @Test
    void moveThrowsExceptionWhenRobotWouldMoveOutOfBoundsMinimumBounds() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.SOUTH);
        robot.setX(0);
        robot.setY(0);

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        MoveCommand robotMoveCommand = new MoveCommand();
        robotMoveCommand.setCommand(Command.MOVE);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), robotMoveCommand));
        assertThat(exception.getMessage(), is("Robot must be moved within the y axis table bounds, 0 to 4. Attempted robot y position: -1"));

        robotMoveCommand.setCommand(Command.RIGHT);
        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);
        robotMoveCommand.setCommand(Command.MOVE);

        exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), robotMoveCommand));
        assertThat(exception.getMessage(), is("Robot must be moved within the x axis table bounds, 0 to 4. Attempted robot x position: -1"));
    }

    @Test
    void moveThrowsExceptionWhenRobotWouldMoveOutOfBoundsMaximumBounds() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.NORTH);
        robot.setX(4);
        robot.setY(4);

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        MoveCommand robotMoveCommand = new MoveCommand();
        robotMoveCommand.setCommand(Command.MOVE);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), robotMoveCommand));
        assertThat(exception.getMessage(), is("Robot must be moved within the y axis table bounds, 0 to 4. Attempted robot y position: 5"));

        robotMoveCommand.setCommand(Command.RIGHT);
        robotService.moveRobot(createdRobot.getId(), robotMoveCommand);
        robotMoveCommand.setCommand(Command.MOVE);

        exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), robotMoveCommand));
        assertThat(exception.getMessage(), is("Robot must be moved within the x axis table bounds, 0 to 4. Attempted robot x position: 5"));
    }

    @Test
    void moveWorkAsExpectedAfterExceptionsAreGiven() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.NORTH);
        robot.setX(4);
        robot.setY(4);

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        MoveCommand moveCommand = new MoveCommand();
        moveCommand.setCommand(Command.MOVE);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), moveCommand));
        assertThat(exception.getMessage(), is("Robot must be moved within the y axis table bounds, 0 to 4. Attempted robot y position: 5"));

        moveCommand.setCommand(Command.LEFT);
        robotService.moveRobot(createdRobot.getId(), moveCommand);
        moveCommand.setCommand(Command.MOVE);

        robotService.moveRobot(createdRobot.getId(), moveCommand);
        assertThat(createdRobot.getFacing(), is(Facing.WEST));
        assertThat(createdRobot.getX(), is(3));
        assertThat(createdRobot.getY(), is(4));
    }

    @Test
    void moveHandlesNullCommands() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robot = new Robot();
        robot.setFacing(Facing.NORTH);

        when(robotTranslator.translateRobot(requestRobot)).thenReturn(robot);

        Robot createdRobot = robotService.createRobot(requestRobot);

        final MoveCommand moveCommand = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), moveCommand));
        assertThat(exception.getMessage(), is("Command is a mandatory value and must not be null"));

        final MoveCommand moveCommandWithNull = new MoveCommand();

        exception = assertThrows(IllegalArgumentException.class, () -> robotService.moveRobot(createdRobot.getId(), moveCommandWithNull));
        assertThat(exception.getMessage(), is("Command is a mandatory value and must not be null"));
    }
}