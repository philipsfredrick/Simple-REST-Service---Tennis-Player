package com.nonso.tennisplayerrest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> playerNotFoundHandler(PlayerNotFoundException ex,
                                                                     HttpServletRequest request) {
        PlayerErrorResponse error = new PlayerErrorResponse(ZonedDateTime.now(), HttpStatus.NOT_FOUND.value(), request.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> genericHandler(Exception ex, HttpServletRequest request) {
        PlayerErrorResponse error1 = new PlayerErrorResponse(ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(error1, HttpStatus.BAD_REQUEST);
    }
}
