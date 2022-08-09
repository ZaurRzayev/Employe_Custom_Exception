package com.example.EmployeeDemo.service;

import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.model.ErrorResponse;
import com.example.EmployeeDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            ErrorResponse erResp = ErrorResponse.builder()
                    .message(id+" This id is not exist")
                    .code("123")
                    .traceId(UUID.randomUUID().toString())
                    .build();
            throw new EmployeeNotFoundException(erResp);
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
}
