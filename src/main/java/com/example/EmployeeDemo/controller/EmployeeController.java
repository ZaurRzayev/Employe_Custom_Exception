/*
create table employee(ID SERIAL PRIMARY KEY ,firstNAME text,lastNAME text,pin int UNIQUE, mobile text, active_status boolean, salary int);
--drop table employee
 */

package com.example.EmployeeDemo.controller;

import com.example.EmployeeDemo.exception.*;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.model.ErrorResponse;
import com.example.EmployeeDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    //private EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
//    ControllerException controllerException;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        if (employeeRepository.existsById(employee.getId())) {
            throw new EmployeeAlreadyExistsException();
        }

        String temUsername = employee.getUsername();
        if(temUsername !=null && !"".equals(temUsername)) {
            Employee userObject = employeeRepository.findByUsername(temUsername);
            if(userObject!=null) {
                throw new EmployeeUsernameAlreadyExistsException();
            }

        }

        String pin = employee.getPin();
        if(pin !=null && !"".equals(pin)) {
            Employee userObject = employeeRepository.findByPin(pin);
            if(userObject!=null) {
                throw new EmployeePinAlreadyExistsException();
            }
        }


        Employee employee1 = employeeRepository.save(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);

    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() throws EmployeeNotFoundException {
        return new ResponseEntity<>((List<Employee>) employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException, EmployeePinAlreadyExistsException {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException();
        } else {
            Employee employee1 = employeeRepository.save(employee);
            return new ResponseEntity<>(employee1, HttpStatus.CREATED);
        }


    }

    @GetMapping("employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id, Employee employee) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException();
        }
        return new ResponseEntity<>(employeeRepository.findById(id), HttpStatus.OK);
    }

    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    public ResponseEntity<?> EmployeeAlreadyExistsException(EmployeeAlreadyExistsException employeeAlreadyExistsException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This Employee already exist!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<?> EmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This id is not valid!")
                .code("404")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = EmployeePinAlreadyExistsException.class)
    public ResponseEntity<?> EmployeePinAlreadyExistsException(EmployeePinAlreadyExistsException employeePinAlreadyExistsException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This pin Already in use!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = EmployeeUsernameAlreadyExistsException.class)
    public ResponseEntity<?> EmployeeUsernameAlreadyExistsException(EmployeeUsernameAlreadyExistsException employeeUsernameAlreadyExistsException) {

        ErrorResponse erResp = ErrorResponse.builder()
                .message("This Username Already in use!")
                .code("109")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }

}
