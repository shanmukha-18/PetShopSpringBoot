package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PetCategoriesDTO;
import com.example.demo.entity.PetCategories;
import com.example.demo.entity.Suppliers;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesInputInvalidException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByIdException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByNameException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundException;
import com.example.demo.exceptionHandler.suppliers.SupplierInputInvalidException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByIdException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByNameException;

import com.example.demo.service.PetCategoriesServiceImpl;

import jakarta.validation.Valid;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")   // Allow CORS from Angular development server
@RequestMapping("/api/v1/categories")   
public class PetCategoriesController {
	@Autowired
	PetCategoriesServiceImpl petCategoriesService;  // Service for pet categories operations
	
	// Retrieve all pet categories and return HTTP status 200 OK
	@GetMapping("/get")
	public ResponseEntity<List<PetCategories>> getAllCategories() throws PetCategoriesNotFoundException{
		return new ResponseEntity<List<PetCategories>>(petCategoriesService.getAllPetCategories(), HttpStatus.OK);
	}
	
	 
	// Retrieve a pet category by ID and return HTTP status 200 OK
	@GetMapping("/{categoryId}")
	public ResponseEntity<PetCategories> getPetCategoriesById(@PathVariable("categoryId") int categoryId) throws PetCategoriesNotFoundByIdException{
		 return new ResponseEntity<PetCategories>(petCategoriesService.getPetCategoriesById(categoryId),HttpStatus.OK);
	}
	
	// Retrieve a pet category by name and return HTTP status 200 OK
	@GetMapping("/name/{name}")
	public ResponseEntity<PetCategories> getCategoriesByName(@PathVariable("name") String name) throws PetCategoriesNotFoundByNameException{
		 return new ResponseEntity<PetCategories>(petCategoriesService.getCategoriesByName(name),HttpStatus.OK);
	}
	
	// Add a new pet category and return HTTP status 201 Created
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategories(@Valid @RequestBody PetCategoriesDTO petCategories) throws PetCategoriesInputInvalidException{
		
		 return new ResponseEntity<ApiResponse>(petCategoriesService.addCategories(petCategories),HttpStatus.CREATED);
	}
	
	 
	// Update an existing pet category by ID and return HTTP status 200 OK
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategories(@PathVariable("categoryId") int categoryId, @Valid @RequestBody PetCategoriesDTO updatedPetCategory) throws PetCategoriesInputInvalidException ,PetCategoriesNotFoundByIdException{
		
		 return new ResponseEntity<ApiResponse>(petCategoriesService.updateCategories(categoryId, updatedPetCategory),HttpStatus.OK);
	}
	
	// Delete a pet category by ID and return HTTP status 200 OK
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deletePetCategory(@PathVariable("categoryId") int categoryId) throws PetCategoriesNotFoundByIdException {
		
		 return new ResponseEntity<ApiResponse>(petCategoriesService.deletePetCategory(categoryId),HttpStatus.OK);
	}
 
}