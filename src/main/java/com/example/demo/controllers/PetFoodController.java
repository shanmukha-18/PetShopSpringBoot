package com.example.demo.controllers;
 
import org.springframework.web.bind.annotation.RestController;
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

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.PetFood;
import com.example.demo.exceptionHandler.petFood.*;
import com.example.demo.service.PetFoodServiceImpl;

import jakarta.validation.Valid;
import java.util.List;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server

@RequestMapping("/api/v1/petfood")  
public class PetFoodController {
 
    @Autowired
    private PetFoodServiceImpl petFoodService;  // Service for pet food operations
 
    
 // Retrieve all pet food items and return HTTP status 200 OK
    @GetMapping("/get")
    public ResponseEntity<List<PetFood>> getAllPetFoods() throws NoPetFoodFoundException {
        List<PetFood> petFoods = petFoodService.retreiveAllPetFood();
        return new ResponseEntity<>(petFoods, HttpStatus.OK);
    }
 
 // Retrieve a specific pet food item by ID and return HTTP status 200 OK
    @GetMapping("/{foodId}")
    public ResponseEntity<PetFood> getPetFoodById(@PathVariable("foodId") int foodId) throws PetFoodIdNotFoundException {
        PetFood petFood = petFoodService.getFoodById(foodId);
        return new ResponseEntity<>(petFood, HttpStatus.OK);
    }
 
 // Retrieve pet food items by name and return HTTP status 200 OK
    @GetMapping("/name/{name}")
    public ResponseEntity<List<PetFood>> getPetFoodByName(@PathVariable("name") String name) throws PetFoodNameNotFoundException {
        List<PetFood> petFoods = petFoodService.getPetFoodByName(name);
        return new ResponseEntity<>(petFoods, HttpStatus.OK);
    }
    
 // Retrieve pet food items by brand name and return HTTP status 200 OK
    @GetMapping("/brand/{name}")
    public ResponseEntity<List<PetFood>> getPetFoodByBrandName(@PathVariable("name") String name) throws PetFoodBrandNotFoundException {
        List<PetFood> petFoods = petFoodService.getPetFoodByBrandName(name);
        return new ResponseEntity<>(petFoods, HttpStatus.OK);
    }
 
    
 // Add a new pet food item and return HTTP status 201 Created
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createPetFood(@Valid @RequestBody PetFood petFood) throws FoodInputInvalidException {
       
        return new ResponseEntity<ApiResponse>(petFoodService.addFood(petFood),HttpStatus.CREATED);
    }
 
    
 // Update an existing pet food item by ID and return HTTP status 200 OK
    @PutMapping("/update/{foodId}")
    public ResponseEntity<ApiResponse> updatePetFood(@PathVariable("foodId") int foodId, @Valid @RequestBody PetFood updatedPetFood)  {
        
        return new ResponseEntity<ApiResponse>(petFoodService.updateFood(foodId, updatedPetFood),HttpStatus.OK);
    }
    
 // Delete a pet food item by ID and return HTTP status 200 OK
    @DeleteMapping("/delete/{foodId}")
    public ResponseEntity<ApiResponse> deleteFood(@PathVariable("foodId") int foodId) throws PetFoodIdNotFoundException {
       
        return new ResponseEntity<ApiResponse>( petFoodService.deleteFood(foodId),HttpStatus.OK);
    }
}