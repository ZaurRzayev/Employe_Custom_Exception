package com.example.EmployeeDemo.exception;

import com.example.EmployeeDemo.model.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.annotation.security.DenyAll;

@Data
@AllArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {

    private ErrorResponse error;

    public EmployeeNotFoundException(String message) {
        super(message);
    }


    public String getErrorCode() {
        return "NE-001";
    }

    public EmployeeNotFoundException() {
    }
}