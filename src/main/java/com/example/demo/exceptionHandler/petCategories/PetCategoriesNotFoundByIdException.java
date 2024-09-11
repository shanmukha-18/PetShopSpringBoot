package com.example.demo.exceptionHandler.petCategories;

public class PetCategoriesNotFoundByIdException  extends Exception{
 public PetCategoriesNotFoundByIdException(String message) {
	 super(message);
 }
}
