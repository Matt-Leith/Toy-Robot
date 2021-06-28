package com.codetest.toyrobot.controller;

import com.codetest.toyrobot.model.Robot;
import com.codetest.toyrobot.model.request.MoveCommand;
import com.codetest.toyrobot.model.request.RequestRobot;
import com.codetest.toyrobot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ToyRobotController {
    private final RobotService robotService;

    @Autowired
    public ToyRobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @RequestMapping(path = "/robot", method = RequestMethod.POST)
    public ResponseEntity<Robot> createRobot(@RequestBody RequestRobot requestRobot) {
        Robot savedRobot = robotService.createRobot(requestRobot);
        return new ResponseEntity<>(
                savedRobot, HttpStatus.CREATED
        );
    }

    @RequestMapping(path = "/robot/{robotId}", method = RequestMethod.PATCH)
    public ResponseEntity<Robot> moveRobot(@PathVariable int robotId, @RequestBody MoveCommand moveCommand) {
        Robot robot = robotService.moveRobot(robotId, moveCommand);
        return new ResponseEntity<>(
                robot, HttpStatus.OK
        );
    }

    @RequestMapping(path = "/robot/{robotId}", method = RequestMethod.GET)
    public ResponseEntity<Robot> getRobot(@PathVariable int robotId) {
        return new ResponseEntity<>(
                robotService.getRobot(robotId), HttpStatus.OK
        );
    }
}
