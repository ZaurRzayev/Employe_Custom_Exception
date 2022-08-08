package com.example.EmployeeDemo.exception;

import java.util.Date;

public class EmployeeTimeStamp {
    Date timestamp;
    String message;

    public EmployeeTimeStamp(Date timestamp,String message){
        this.message=message;
        this.timestamp=timestamp;
    }
}
