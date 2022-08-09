package com.example.EmployeeDemo.exception;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {



    private String message2;


    private String message3;

    public EmployeeGlobalExceptionHandler() {
    }


    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
        return new ResponseEntity<>(employeeNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}