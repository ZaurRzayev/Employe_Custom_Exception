/*
create table employee(ID SERIAL PRIMARY KEY ,firstNAME text,lastNAME text,pin int UNIQUE, mobile text, active_status boolean, sasalary int);
--drop table employee
 */

package com.example.EmployeeDemo.controller;
import com.example.EmployeeDemo.exception.ControllerException;
import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.exception.EmployeePinAlreadyExistsException;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.model.ErrorResponse;
import com.example.EmployeeDemo.repository.EmployeeRepository;
import com.example.EmployeeDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    //private EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        if (employeeRepository.existsById(employee.getId())) {
            throw new EmployeeAlreadyExistsException();
        }
        Employee employee1 = employeeRepository.save(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);

    }



    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() throws EmployeeNotFoundException {
        return new ResponseEntity<>((List<Employee>) employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException,EmployeePinAlreadyExistsException {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException();
        }else {
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

//        ControllerException ce = new ControllerException(employeeNotFoundException,new Date(),"349","not found 404","Heyyyy!");
//        return new ResponseEntity<>(ce, HttpStatus.CONFLICT);
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
                .message("This Pin Already in use!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }


}
