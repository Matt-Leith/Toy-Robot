package com.codetest.toyrobot.controller;

import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.MoveCommand;
import com.codetest.toyrobot.model.request.RequestRobot;
import com.codetest.toyrobot.service.RobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToyRobotControllerTest {
    @Mock
    private RobotService robotService;

    private ToyRobotController toyRobotController;

    @BeforeEach
    void setUp() {
        toyRobotController = new ToyRobotController(robotService);
    }

    @Test
    void controllerDelegatesToServiceForMove() {
        int robotId = 0;
        MoveCommand moveCommand = new MoveCommand();

        Robot robotReturnedFromService = new Robot();

        when(robotService.moveRobot(robotId, moveCommand)).thenReturn(robotReturnedFromService);

        ResponseEntity<Robot> robotResponseEntity = toyRobotController.moveRobot(robotId, moveCommand);

        verify(robotService).moveRobot(robotId, moveCommand);

        assertThat(robotResponseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(robotResponseEntity.getBody(), is(robotReturnedFromService));
    }

    @Test
    void controllerDelegatesToServiceForGet() {
        int robotId = 0;

        Robot robotReturnedFromService = new Robot();

        when(robotService.getRobot(robotId)).thenReturn(robotReturnedFromService);

        ResponseEntity<Robot> robotResponseEntity = toyRobotController.getRobot(robotId);

        verify(robotService).getRobot(robotId);

        assertThat(robotResponseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(robotResponseEntity.getBody(), is(robotReturnedFromService));
    }

    @Test
    void controllerDelegatesToServiceForCreate() {
        RequestRobot requestRobot = new RequestRobot();

        Robot robotReturnedFromService = new Robot();

        when(robotService.createRobot(requestRobot)).thenReturn(robotReturnedFromService);

        ResponseEntity<Robot> robotResponseEntity = toyRobotController.createRobot(requestRobot);

        verify(robotService).createRobot(requestRobot);

        assertThat(robotResponseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(robotResponseEntity.getBody(), is(robotReturnedFromService));
    }
}