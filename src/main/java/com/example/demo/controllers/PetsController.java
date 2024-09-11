package com.example.demo.controllers;
 
import java.util.List;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.example.demo.dto.PetDTO;
import com.example.demo.entity.PetCategories;
import com.example.demo.entity.Pets;
import com.example.demo.entity.Suppliers;
import com.example.demo.entity.Transactions;
import com.example.demo.exceptionHandler.pets.FoodBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.InvalidPetInputException;
import com.example.demo.exceptionHandler.pets.PetAlreadyExistsException;
import com.example.demo.exceptionHandler.pets.PetCategoryNotFoundException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.pets.PetsNotFoundException;
import com.example.demo.exceptionHandler.pets.SupplierBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.TransactionBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.VaccinationBasedPetsNotFoundException;
import com.example.demo.repository.PetCategoriesRepository;
import com.example.demo.service.PetServiceImpl;
 
import jakarta.validation.Valid;
 
@RestController
//Allow CORS from Angular development server
@CrossOrigin(origins = "http://localhost:4200")  
@RequestMapping("/api/v1/pets")
public class PetsController {
	@Autowired
	PetServiceImpl petService;  // Service for pet operations
	
	@Autowired
	private PetCategoriesRepository petCategoriesRepository;   // Repository for pet categories
	
	 
	// Retrieve all pets and return HTTP status 200 OK
	@GetMapping("/get")
	public ResponseEntity<List<Pets>>getAllPets() throws PetsNotFoundException{
		return new ResponseEntity<List<Pets>>(petService.getAllPets(),HttpStatus.OK);
	}
	
	// Retrieve a specific pet by ID and return HTTP status 200 OK
	@GetMapping("/{petId}")
	public ResponseEntity<Pets>getPetsById(@PathVariable("petId") int petId) throws PetsNotFoundException, PetIdNotFoundException{
		return new ResponseEntity<Pets>(petService.getPetsById(petId),HttpStatus.OK);
	}
	

//	@GetMapping("/categories/get")
//	public ResponseEntity<List<Pets>>getAllPetCategories() throws PetsNotFoundException{
//		return new ResponseEntity<List<Pets>>(petService.getAllPetCategories(),HttpStatus.OK);
//	}
//	
	
	 
	// Retrieve pets by category and return HTTP status 200 OK
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Pets>>getPetsByCategory(@PathVariable("category") String category) throws PetCategoryNotFoundException{
		return new ResponseEntity<List<Pets>>(petService.getPetsByCategory(category),HttpStatus.OK);
	}
	
	// Retrieve pets by breed name and return HTTP status 200 OK
	@GetMapping("/breed/{breed}")
	public ResponseEntity<List<Pets>>getPetsByBreedName(@PathVariable("breed") String breed) throws PetCategoryNotFoundException{
		return new ResponseEntity<List<Pets>>(petService.getPetsByBreed(breed),HttpStatus.OK);
	}
	
	 
	// Retrieve pets by vaccination ID and return HTTP status 200 OK
	@GetMapping("/vaccinations/{petId}")
	public ResponseEntity<List<Pets>> getPetsByVaccination(@PathVariable("petId") int petId) throws PetIdNotFoundException, VaccinationBasedPetsNotFoundException{
		return new ResponseEntity<List<Pets>>(petService.getPetsByVaccination(petId),HttpStatus.OK);
	}
	
	// Retrieve pet food information by pet ID and return HTTP status 200 OK
	@GetMapping("/food_info/{petId}")
	public ResponseEntity<List<Object[]>>getPetFoodByPetId(@PathVariable("petId") int petId) throws PetIdNotFoundException, FoodBasedPetsNotFoundException{
		return new ResponseEntity<List<Object[]>>(petService.getPetFoodByPetId(petId),HttpStatus.OK);
	}
	
	// Retrieve supplier information by pet ID and return HTTP status 200 OK
	@GetMapping("/suppliers/{petId}")
	public ResponseEntity<List<Object[]>>getSupplierByPetId(@PathVariable("petId") int petId) throws PetIdNotFoundException, SupplierBasedPetsNotFoundException{
		return new ResponseEntity<List<Object[]>>(petService.getSupplierByPetId(petId),HttpStatus.OK);
	}
	
	// Retrieve transaction history by pet ID and return HTTP status 200 OK
	@GetMapping("/transaction_history/{petId}")
	public ResponseEntity<Transactions>getTransactionByPetId(@PathVariable("petId") int petId) throws PetIdNotFoundException, TransactionBasedPetsNotFoundException{
		return new ResponseEntity<Transactions>(petService.getTransactionByPetId(petId),HttpStatus.OK);
	}
	
	// Add a new pet and return HTTP status 201 Created or 500 Internal Server Error if exception occurs
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addPet( @RequestBody @Valid PetDTO petDTO) {
		  try {
//	            Pets pet = petService.addPet(petDTO);
	            return new ResponseEntity<ApiResponse>(petService.addPet(petDTO), HttpStatus.CREATED);
	        } catch (Exception e) {
	            // Log the error and return a suitable response
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
	
	// Update an existing pet and return HTTP status 200 OK or 500 Internal Server Error if exception occurs
	@PutMapping("/update/{petId}")
	public ResponseEntity<ApiResponse> updatePet(@PathVariable("petId") int petId, @Valid @RequestBody PetDTO petDTO) throws PetIdNotFoundException, InvalidPetInputException{
		 try {
//	            Pets pet = petService.updatePet(petId,petDTO);
	            return new ResponseEntity<ApiResponse>(petService.updatePet(petId,petDTO), HttpStatus.CREATED);
	        } catch (Exception e) {
	            // Log the error and return a suitable response
	            return new ResponseEntity<ApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
	
	// Delete a pet by ID and return HTTP status 200 OK
	@DeleteMapping("/delete/{petId}")
	public ResponseEntity<ApiResponse> deletePet(@PathVariable("petId") int petId) throws PetIdNotFoundException{
	
		return new ResponseEntity<ApiResponse>(	petService.deletePet(petId),HttpStatus.OK);
	} 
}