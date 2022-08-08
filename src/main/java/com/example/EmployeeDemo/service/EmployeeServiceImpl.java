package com.example.EmployeeDemo.service;

import com.example.EmployeeDemo.exception.EmployeeAlreadyExistsException;
import com.example.EmployeeDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeDemo.model.Employee;
import com.example.EmployeeDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Employee saveEmployee = employeeRepository.save(employee);
        return saveEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee;
        if (employeeRepository.findById(id).isEmpty()) {
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
        Employee saveEmployee = employeeRepository.save(employee);
        return saveEmployee;
    }
}
