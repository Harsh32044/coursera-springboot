package com.udemy.dev.errorhandling;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class _ExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Object> handleInvalidRoutes(ServletException e) {
        return new ResponseEntity<>("404 - You chose the wrong route son.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<DataNotFoundResponse> handleDataNotFound(Exception e) {

        DataNotFoundResponse dataNotFoundResponse = new DataNotFoundResponse();

        dataNotFoundResponse.setMessage(e.getMessage());
        dataNotFoundResponse.setTimeStamp(System.currentTimeMillis());
        dataNotFoundResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(dataNotFoundResponse, HttpStatus.NOT_FOUND);
    }
}
