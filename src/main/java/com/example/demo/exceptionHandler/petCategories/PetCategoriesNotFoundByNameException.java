package com.example.demo.exceptionHandler.petCategories;

public class PetCategoriesNotFoundByNameException  extends Exception{
 public PetCategoriesNotFoundByNameException(String message) {
	 super(message);
 }
}
