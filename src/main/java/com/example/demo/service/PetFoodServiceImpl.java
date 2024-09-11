package com.example.demo.service;
 
 import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.PetFood;
import com.example.demo.exceptionHandler.petFood.FoodInputInvalidException;
import com.example.demo.repository.PetFoodRepository;

import jakarta.transaction.Transactional;

import com.example.demo.exceptionHandler.petFood.*;
 
 
@Service
public class PetFoodServiceImpl implements PetFoodService {
 
	@Autowired
	PetFoodRepository petFoodRepository;

	// Retrieve all pet food items
	public List<PetFood> retreiveAllPetFood() throws NoPetFoodFoundException{
		List<PetFood> petfood=petFoodRepository.findAll();
		if(petfood.isEmpty())
		{
			throw new NoPetFoodFoundException("Validation Failed");
		}
		return petfood;
	}

	// Retrieve pet food by ID
	public PetFood getFoodById(int foodId) throws PetFoodIdNotFoundException
	{
		if(petFoodRepository.findById(foodId).isEmpty()) throw new PetFoodIdNotFoundException("PetFood is not found by Id");
		else {
			return petFoodRepository.findById(foodId).get();
		}
	}

 
	  
	// Retrieve pet food items by name
	public List<PetFood> getPetFoodByName(String name) throws PetFoodNameNotFoundException{
		
		List<PetFood> petfood=petFoodRepository.findByName(name);
		if(petfood.isEmpty())
			throw new PetFoodNameNotFoundException("PetFood is not found by name");
		return petfood;
	}
	
	// Retrieve pet food items by brand name
	public List<PetFood> getPetFoodByBrandName(String brand)throws PetFoodBrandNotFoundException {
		List<PetFood> petfood=petFoodRepository.findByBrand(brand);
		if(petfood.isEmpty())
			throw new PetFoodBrandNotFoundException("PetFood is not found by Brand name");
		
        return petfood;
    }
 
 
	// Add a new pet food item
	@Override
	@Transactional
	public ApiResponse addFood(PetFood petfood) throws FoodInputInvalidException{
		
		try {
			petFoodRepository.save(petfood);
			return new ApiResponse(LocalDate.now(),"PetFood added successfully");
		} catch (Exception e) {
			throw new FoodInputInvalidException("PetFood cannot be inserted");
		}
 
	}


    
// Update an existing pet food item
	@Override
	@Transactional
	public ApiResponse updateFood(int food_id, PetFood updatedPetFoods)  {
		// TODO Auto-generated method stub
		PetFood petfood=petFoodRepository.findById(food_id).get();
			if(petfood!=null)
			{
				petfood.setName(updatedPetFoods.getName());
				petfood.setBrand(updatedPetFoods.getBrand());
				petfood.setType(updatedPetFoods.getType());
				petfood.setQuantity(updatedPetFoods.getQuantity());
				petfood.setPrice(updatedPetFoods.getPrice());
			}
			petFoodRepository.save(petfood);
			return new ApiResponse(LocalDate.now(),"PetFood updated successfully");

 
	}
 
	// Delete pet food item from the repository
	@Transactional
	public ApiResponse deleteFood(int food_id) throws PetFoodIdNotFoundException {
		PetFood petfood=petFoodRepository.findById(food_id).get();
		if(petfood==null) {
			throw new PetFoodIdNotFoundException("pet food not found with id");
		}else {
			petFoodRepository.deleteById(food_id);
			return new ApiResponse(LocalDate.now(),"PetFood deleted successfully");
		}
	}
	}