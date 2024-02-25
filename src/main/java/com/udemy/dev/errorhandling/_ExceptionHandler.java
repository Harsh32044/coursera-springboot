package com.udemy.dev.errorhandling;

import jakarta.servlet.ServletException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

@ControllerAdvice
public class _ExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<GenericServerErrorResponse> handleInvalidRoutes(ServletException e) {
        GenericServerErrorResponse objDnf = new GenericServerErrorResponse();

        objDnf.setMessage(e.getMessage());
        objDnf.setStatus(HttpStatus.NOT_FOUND.value());
        objDnf.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(objDnf, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericServerErrorResponse> handleDataNotFound(GenericException e) {

        GenericServerErrorResponse genericServerErrorResponse = new GenericServerErrorResponse();

        genericServerErrorResponse.setMessage(e.getMessage());
        genericServerErrorResponse.setTimeStamp(System.currentTimeMillis());
        genericServerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(genericServerErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<GenericServerErrorResponse> handleServerErrors(SQLException e) {
        GenericServerErrorResponse genericServerErrorResponse = new GenericServerErrorResponse();

        genericServerErrorResponse.setMessage(e.getMessage());
        genericServerErrorResponse.setTimeStamp(System.currentTimeMillis());
        genericServerErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(genericServerErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
