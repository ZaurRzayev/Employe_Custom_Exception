package com.example.EmployeeDemo.service;

import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.exception.EmployeePinAlreadyExistsException;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.model.ErrorResponse;
import com.example.EmployeeDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            throw new EmployeeAlreadyExistsException();
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee;
        if (employeeRepository.findById(id).isEmpty()) {
//            ErrorResponse erResp = ErrorResponse.builder()
//                    .message(id + " This id is not exist")
//                    .code("123")
//                    .traceId(UUID.randomUUID().toString())
//                    .build();
//            throw new EmployeeNotFoundException(erResp);
            throw new EmployeeNotFoundException();
        } else {
            employee = employeeRepository.findById(id).get();
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException();
        }
        return employeeRepository.save(employee);
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
                .message("This Pin Already in use!")
                .code("101")
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(erResp, HttpStatus.CONFLICT);

    }
}
