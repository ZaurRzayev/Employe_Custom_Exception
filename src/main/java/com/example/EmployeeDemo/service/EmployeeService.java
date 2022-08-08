package com.example.EmployeeDemo.service;

import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException;
    List<Employee> getAllEmployee() throws EmployeeNotFoundException;
    Employee getEmployeeById(int id) throws EmployeeNotFoundException;
    Employee updateEmployee(Employee employee) throws  EmployeeNotFoundException;
}
