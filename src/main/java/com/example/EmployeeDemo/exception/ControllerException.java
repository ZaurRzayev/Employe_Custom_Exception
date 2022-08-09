package com.example.EmployeeDemo.exception;

import java.util.Date;

public class ControllerException extends RuntimeException{


    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
    private Date timestamp;
    private String message;
    Exception stackTrace;


    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public ControllerException(Exception stackTrace,Date timestamp,String errorCode, String errorMessage, String message) {
        super();
        this.timestamp=timestamp;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.message=message;
    }

    public ControllerException() {

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
