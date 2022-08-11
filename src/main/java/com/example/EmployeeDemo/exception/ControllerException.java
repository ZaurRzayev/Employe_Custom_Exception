package com.example.EmployeeDemo.exception;
import com.example.EmployeeDemo.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.UUID;

public class ControllerException extends RuntimeException {

    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    public ResponseEntity<?> EmployeeAlreadyExist(EmployeeAlreadyExistsException employeeAlreadyExistsException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This Employee already exist!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<?> EmployeeNotFound(EmployeeNotFoundException employeeNotFoundException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This id is not valid!")
                .code("404")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = EmployeePinAlreadyExistsException.class)
    public ResponseEntity<?> EmployeePinAlreadyExists(EmployeePinAlreadyExistsException employeePinAlreadyExistsException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This Pin Already in use!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }
}
