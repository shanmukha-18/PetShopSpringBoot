package com.example.demo.service;
 
import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.PetFood;
import com.example.demo.exceptionHandler.petFood.FoodInputInvalidException;
import com.example.demo.exceptionHandler.petFood.NoPetFoodFoundException;
import com.example.demo.exceptionHandler.petFood.PetFoodIdNotFoundException;
import com.example.demo.exceptionHandler.petFood.PetFoodNameNotFoundException;
 
 
import com.example.demo.exceptionHandler.petFood.*;
 
public interface PetFoodService {
 
	public List<PetFood> retreiveAllPetFood()throws NoPetFoodFoundException;
	public PetFood getFoodById(int foodId)throws PetFoodIdNotFoundException;
	public List<PetFood> getPetFoodByName(String name)throws PetFoodNameNotFoundException;
	public List<PetFood> getPetFoodByBrandName(String brand)throws PetFoodBrandNotFoundException;
	public ApiResponse addFood(PetFood petfood)throws FoodInputInvalidException;
	public ApiResponse updateFood(int food_id, PetFood updatedPetFoods);
	ApiResponse deleteFood(int food_id) throws PetFoodIdNotFoundException;
}