package com.example.demo.controllers;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.example.demo.entity.Employees;
import com.example.demo.exceptionHandler.employees.EmployeeInputInvalidException;
import com.example.demo.exceptionHandler.employees.EmployeeNotFoundException;
import com.example.demo.exceptionHandler.employees.EmployeesNotFoundException;
import com.example.demo.service.EmployeeServiceImpl;
 
import jakarta.validation.Valid;
import java.util.List;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server
@RequestMapping("/api/v1/employees")  // Base path for employee-related endpoints
public class EmployeesController {
 
    @Autowired
    private EmployeeServiceImpl employeeService;  // Service layer for employee operations
 
    
 // Retrieve all employees and return HTTP status 200 OK
    @GetMapping("/get")
    public ResponseEntity<List<Employees>> getAllEmployees() throws EmployeesNotFoundException {
        List<Employees> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
 // Retrieve a specific employee by ID and return HTTP status 200 OK
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException {
        Employees employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
 
    
 // Retrieve employees by first name and return HTTP status 200 OK
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Employees>> getEmployeesByFirstName(@PathVariable("firstName") String firstName) throws EmployeeNotFoundException {
        List<Employees> employees = employeeService.getEmployeesByFirstName(firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
    
 // Retrieve employees by last name and return HTTP status 200 OK
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Employees>> getEmployeesByLastName(@PathVariable("lastName") String lastName) throws EmployeeNotFoundException {
        List<Employees> employees = employeeService.getEmployeesByLastName(lastName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
 // Retrieve employees by position and return HTTP status 200 OK
    @GetMapping("/position/{position}")
    public ResponseEntity<List<Employees>> getEmployeesByPosition(@PathVariable("position") String position) throws EmployeeNotFoundException {
        List<Employees> employees = employeeService.getEmployeesByPosition(position);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
 
    
 // Add a new employee and return HTTP status 201 Created
    @PostMapping("/add")
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employees employee) throws EmployeeInputInvalidException {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    
 // Update an existing employee by ID and return HTTP status 200 OK
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("employeeId") int employeeId, @Valid @RequestBody Employees updatedEmployee) throws EmployeeNotFoundException {
        employeeService.updateEmployee(employeeId, updatedEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
 // Delete an employee by ID and return HTTP status 200 OK
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") int employeeId) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}