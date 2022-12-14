package com.example.EmployeeDemo.exception;


import com.example.EmployeeDemo.model.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAlreadyExistsException extends RuntimeException {
    private String message;

    private ErrorResponse error;

    public EmployeeAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
