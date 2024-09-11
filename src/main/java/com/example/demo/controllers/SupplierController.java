package com.example.demo.controllers;
 
import org.springframework.web.bind.annotation.RestController;
 
import java.util.*;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.Suppliers;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.suppliers.*;
import com.example.demo.service.SuppliersService;

import jakarta.validation.Valid;
 
 
@RestController
@RequestMapping("/api/v1/suppliers")  // Base path for supplier endpoints
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")  //Allow CORS from Angular development server
 
 
public class SupplierController {
	@Autowired
	SuppliersService suppliersService;  // Service for supplier operations
	
	 
	// Retrieve all suppliers and return HTTP status 200 OK
	@GetMapping("/get")
	public ResponseEntity<List<Suppliers>>getAllSuppliers() throws SuppliersNotFoundException{
		 return new ResponseEntity<List<Suppliers>>(suppliersService.getAllSuppliers(),HttpStatus.OK);
	}
 
	 
	// Retrieve a specific supplier by ID and return HTTP status 200 OK
	@GetMapping("/{supplierId}")
	public ResponseEntity<Suppliers>getSupplierById(@PathVariable("supplierId") int supplierId) throws SupplierNotFoundByIdException{
		 return new ResponseEntity<Suppliers>(suppliersService.getSupplierById(supplierId),HttpStatus.OK);
	}
	
	// Retrieve suppliers by city and return HTTP status 200 OK
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Suppliers>>getSuppliersByCity(@PathVariable("city") String city) throws SupplierNotFoundByCityException{
		 return new ResponseEntity<List<Suppliers>>(suppliersService.getSuppliersByCity(city), HttpStatus.OK);
	}
	
	 
	// Retrieve a specific supplier by name and return HTTP status 200 OK
	@GetMapping("/name/{name}")
	public ResponseEntity<Suppliers>getSupplierByName(@PathVariable("name") String name) throws SupplierNotFoundByNameException{
		 return new ResponseEntity<Suppliers>(suppliersService.getSuppliersByName(name),HttpStatus.OK);
	}
	
	// Create a new supplier and return HTTP status 201 Created
	@PostMapping("/add")
	public ResponseEntity<ApiResponse>createSupplier(@Valid @RequestBody SupplierDTO supplier) throws SupplierInputInvalidException{
	
		 return new ResponseEntity<ApiResponse>(suppliersService.addSupplier(supplier),HttpStatus.CREATED);
	}
	
	 
	// Update an existing supplier and return HTTP status 200 OK
	@PutMapping("/update/{supplierId}")
	public ResponseEntity<ApiResponse>updateSupplier(@PathVariable("supplierId") int supplierId, @Valid @RequestBody SupplierDTO updatedSupplier) throws MethodArgumentNotValidException, SupplierNotFoundByIdException{
		
		 return new ResponseEntity<ApiResponse>(suppliersService.updateSupplier(supplierId,updatedSupplier),HttpStatus.OK);
	}
	
	 
	// Delete a supplier by ID and return HTTP status 200 OK
	@DeleteMapping("/delete/{supplierId}")
	public ResponseEntity<ApiResponse> deleteSupplier(@PathVariable("supplierId") int supplierId) throws SupplierNotFoundByIdException{
		
		return new ResponseEntity<ApiResponse>(suppliersService.deleteSupplier(supplierId),HttpStatus.OK);
	}

	

}
