package com.example.demo.service;
 
import java.util.List;
 
import com.example.demo.entity.Employees;
import com.example.demo.exceptionHandler.employees.EmployeeInputInvalidException;
import com.example.demo.exceptionHandler.employees.EmployeeNotFoundException;
import com.example.demo.exceptionHandler.employees.EmployeesNotFoundException;
 
public interface EmployeeService {
 
    List<Employees> getAllEmployees() throws EmployeesNotFoundException;
 
    Employees getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employees> getEmployeesByFirstName(String firstName) throws EmployeeNotFoundException;
    List<Employees> getEmployeesByLastName(String lastName) throws EmployeeNotFoundException;
 
    List<Employees> getEmployeesByPosition(String position) throws EmployeeNotFoundException;
 
    void addEmployee(Employees employees) throws EmployeeInputInvalidException;
 
    void updateEmployee(int employeeId, Employees updatedEmployee) throws EmployeeNotFoundException;
 
    void deleteEmployee(int employeeId) throws EmployeeNotFoundException;
}