package com.example.EmployeeDemo.exception;

import com.example.EmployeeDemo.model.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUsernameAlreadyExistsException extends RuntimeException {
    private String message;

    private ErrorResponse error;

    public EmployeeUsernameAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }


}

