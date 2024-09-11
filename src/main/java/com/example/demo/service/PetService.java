package com.example.demo.service;
 
 
import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PetDTO;
import com.example.demo.entity.Pets;
import com.example.demo.entity.Transactions;
import com.example.demo.exceptionHandler.*;
import com.example.demo.exceptionHandler.pets.FoodBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.InvalidPetInputException;
import com.example.demo.exceptionHandler.pets.PetAlreadyExistsException;
import com.example.demo.exceptionHandler.pets.PetCategoryNotFoundException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.pets.PetsNotFoundException;
import com.example.demo.exceptionHandler.pets.SupplierBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.VaccinationBasedPetsNotFoundException;
 
public interface PetService {
	
	List<Pets> getAllPets() throws PetsNotFoundException;
//	List<Pets> getAllPetCategories() throws PetsNotFoundException;
	 public List<Pets> getPetsByBreed(String breed) ;
	public Pets getPetsById(int id) throws PetIdNotFoundException;
	List<Pets> getPetsByCategory(String category) throws PetCategoryNotFoundException;
	public List<Object[]> getPetFoodByPetId(int pet_id) throws FoodBasedPetsNotFoundException, PetIdNotFoundException;
	Transactions getTransactionByPetId(int pet_id) throws PetIdNotFoundException;
	List<Object[]> getSupplierByPetId(int pet_id) throws SupplierBasedPetsNotFoundException, PetIdNotFoundException;
	List<Pets> getPetsByVaccination(int pet_id) throws VaccinationBasedPetsNotFoundException, PetIdNotFoundException;
	ApiResponse addPet(PetDTO pet) throws PetAlreadyExistsException, InvalidPetInputException;
	ApiResponse updatePet(int petId, PetDTO pet) throws PetIdNotFoundException, InvalidPetInputException;
    ApiResponse deletePet(int petId) throws PetIdNotFoundException;
 
}