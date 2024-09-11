package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PetCategoriesDTO {
	private int categoryId;
	
	@NotBlank(message = "Category name is mandatory")
	private String name;
    
	
	public int getCategoryId() {
		return categoryId;
	}
 
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 	
	public PetCategoriesDTO(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
 
	@Override
	public String toString() {
		return "petCategoriesDTO [categoryId=" + categoryId + ", name=" + name + "]";
	}
 
	public PetCategoriesDTO() {
		
	}
	
 
}