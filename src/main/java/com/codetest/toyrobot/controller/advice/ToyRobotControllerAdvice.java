package com.codetest.toyrobot.controller.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ToyRobotControllerAdvice {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<String> handleInvalidInput(RuntimeException ex) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(
                bodyOfResponse, HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { InvalidFormatException.class })
    public ResponseEntity<String> handleInvalidFormat(RuntimeException ex) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(
                bodyOfResponse, HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { JsonParseException.class })
    public ResponseEntity<String> handleInvalidJson(RuntimeException ex) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(
                bodyOfResponse, HttpStatus.BAD_REQUEST
        );
    }
}
