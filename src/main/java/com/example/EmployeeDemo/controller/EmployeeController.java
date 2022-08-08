package com.example.EmployeeDemo.controller;
import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        Employee employee1 = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);

    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() throws EmployeeNotFoundException {
        return new ResponseEntity<List<Employee>>((List<Employee>) employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    public ResponseEntity<String> EmployeeAlreadyExistsException(EmployeeAlreadyExistsException employeeAlreadyExistsException) {
        return new ResponseEntity<String>("Employee already exists", HttpStatus.CONFLICT);
    }
}
