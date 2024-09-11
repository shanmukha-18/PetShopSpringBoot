package com.example.demo.service;
 
import java.util.List;

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
 
 
 
public interface PetCategoriesService {
	List<PetCategories> getAllPetCategories() throws PetCategoriesNotFoundException;
	PetCategories getPetCategoriesById(int categoryId) throws PetCategoriesNotFoundByIdException ;
	PetCategories getCategoriesByName(String name) throws PetCategoriesNotFoundByNameException;
	ApiResponse addCategories(PetCategoriesDTO petCategory) throws PetCategoriesInputInvalidException;
	ApiResponse updateCategories(int categoryId,  PetCategoriesDTO updatedPetCategory) throws PetCategoriesNotFoundByIdException,PetCategoriesInputInvalidException;
	ApiResponse deletePetCategory(int categoryId) throws PetCategoriesNotFoundByIdException;
 
}