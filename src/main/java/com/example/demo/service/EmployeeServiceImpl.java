package com.example.demo.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.demo.entity.Employees;
import com.example.demo.exceptionHandler.employees.EmployeeInputInvalidException;
import com.example.demo.exceptionHandler.employees.EmployeeNotFoundException;
import com.example.demo.exceptionHandler.employees.EmployeesNotFoundException;
import com.example.demo.repository.EmployeesRepository;

import jakarta.transaction.Transactional;
 
@Service
public class EmployeeServiceImpl implements EmployeeService {
 
    @Autowired
    private EmployeesRepository employeesRepository;
 
    
 // Retrieve all employees from the repository
    @Override
    public List<Employees> getAllEmployees() throws EmployeesNotFoundException {
        List<Employees> employees = employeesRepository.findAll();
        if (employees.isEmpty()) {
            throw new EmployeesNotFoundException("No employees found.");
        }
        return employees;
    }
 
 // Retrieve employee by ID
    @Override
    public Employees getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employees> employee = employeesRepository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
        }
        return employee.get();
    }
 
 // Retrieve employees by first name
    @Override
    public List<Employees> getEmployeesByFirstName(String firstName) throws EmployeeNotFoundException {
        List<Employees> employees = employeesRepository.findByFirstName(firstName);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with first name: " + firstName);
        }
        return employees;
    }
 
    
 // Retrieve employees by last name
    @Override
    public List<Employees> getEmployeesByLastName(String lastName) throws EmployeeNotFoundException {
        List<Employees> employees = employeesRepository.findByLastName(lastName);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with last name: " + lastName);
        }
        return employees;
    }
 
 // Retrieve employees by position
    @Override
    public List<Employees> getEmployeesByPosition(String position) throws EmployeeNotFoundException {
        List<Employees> employees = employeesRepository.findByPosition(position);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with position: " + position);
        }
        return employees;
    }
    
    
 
    @Override
    @Transactional
    public void addEmployee(Employees employee) throws EmployeeInputInvalidException {
        try {
            employeesRepository.save(employee);
        } catch (Exception e) {
            throw new EmployeeInputInvalidException("Employee cannot be added: " + e.getMessage());
        }
    }
 
    
// Update existing employee details
    @Override
    @Transactional
    public void updateEmployee(int employeeId, Employees updatedEmployee) throws EmployeeNotFoundException {
        Optional<Employees> existingEmployeeOpt = employeesRepository.findById(employeeId);
        if (existingEmployeeOpt.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
        }
 
        Employees existingEmployee = existingEmployeeOpt.get();
        try {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setPosition(updatedEmployee.getPosition());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            existingEmployee.setAddress(updatedEmployee.getAddress());
 
            employeesRepository.save(existingEmployee);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee cannot be updated: " + e.getMessage());
        }
    }
 

    @Override
    @Transactional
    public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<Employees> existingEmployeeOpt = employeesRepository.findById(employeeId);
        if (existingEmployeeOpt.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
        }
 
        try {
            employeesRepository.delete(existingEmployeeOpt.get());
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee cannot be deleted: " + e.getMessage());
        }
    }
}