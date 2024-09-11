package com.example.demo.service;
 
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PetCategoriesDTO;
import com.example.demo.entity.PetCategories;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesInputInvalidException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByIdException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByNameException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundException;
import com.example.demo.repository.PetCategoriesRepository;

import jakarta.transaction.Transactional;
 
 
@Service
public class PetCategoriesServiceImpl implements PetCategoriesService{

		@Autowired
		PetCategoriesRepository petCategoriesRepository;
 
	    
		// Retrieve all pet categories
		@Override
		public List<PetCategories> getAllPetCategories() throws PetCategoriesNotFoundException {
			if(petCategoriesRepository.findAll().isEmpty()) {
				throw new PetCategoriesNotFoundException("No Pet Categories are found");
			}else {
				return petCategoriesRepository.findAll();
			}
		}
 
	    
		// Retrieve pet category by ID
		@Override
		public PetCategories getPetCategoriesById(int categoryId) throws PetCategoriesNotFoundByIdException {
			if(petCategoriesRepository.findById(categoryId).isEmpty()) {
				throw new PetCategoriesNotFoundByIdException("Pet Category is not found with given Id");
			}else {
				return petCategoriesRepository.findById(categoryId).get();
			}
		}
 
		// Retrieve pet category by name
		@Override
		public PetCategories getCategoriesByName(String name) throws PetCategoriesNotFoundByNameException {
			if(petCategoriesRepository.findByName(name)==null) {
				throw new PetCategoriesNotFoundByNameException("Pet Category is Not Found by Name");
			}else {
				return petCategoriesRepository.findByName(name);
			}
		}
 
		@Override
		@Transactional
		   public ApiResponse addCategories(PetCategoriesDTO petCategoriesDTO) throws PetCategoriesInputInvalidException {
	        PetCategories petCategory = new PetCategories();
	        petCategory.setName(petCategoriesDTO.getName());
	        try {
	        	petCategoriesRepository.save(petCategory);
	        	return new ApiResponse(LocalDate.now(), "Pet Category added successfully");
	        } catch (Exception e) {
	            throw new PetCategoriesInputInvalidException("Pet Category cannot be inserted: " + e.getMessage());
	        }
	        }
 
		// Update pet category details
		@Override
		@Transactional
		 public ApiResponse updateCategories(int categoryId, PetCategoriesDTO petCategoriesDTO) throws PetCategoriesNotFoundByIdException, PetCategoriesInputInvalidException {
	        PetCategories petCategory = petCategoriesRepository.findById(categoryId)
	                .orElseThrow(() -> new PetCategoriesNotFoundByIdException("Pet Category not found with id " + categoryId));
	        petCategory.setName(petCategoriesDTO.getName());
	        try {
	        	petCategoriesRepository.save(petCategory);
	        	return new ApiResponse(LocalDate.now(),"Pet category updated successfully");
	        } catch (Exception e) {
	            throw new PetCategoriesInputInvalidException("Pet Category cannot be updated: " + e.getMessage());
	        }
	    }
 
		// Delete pet category from the repository
		@Override
		@Transactional
		   public ApiResponse deletePetCategory(int categoryId) throws PetCategoriesNotFoundByIdException {
	        PetCategories petCategory = petCategoriesRepository.findById(categoryId)
	                .orElseThrow(() -> new PetCategoriesNotFoundByIdException("Pet Category not found with id " + categoryId));
	        petCategoriesRepository.delete(petCategory);
	        return new ApiResponse(LocalDate.now(),"Pet category deleted successfully");
	    }
 
		

 
}