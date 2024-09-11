package com.example.demo.entity;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "pet_categories")
public class PetCategories {
 
  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;
 
    @Column(name = "name")
    @Size(min=3, message = "Brand must be atleast 3 characters")
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, message = "Name must be atleast 3 characters")
    private String name;
    
    @OneToMany(mappedBy="category", cascade=CascadeType.ALL,orphanRemoval=true)
    @JsonBackReference
	private List<Pets> pets;

 
	public int getCategoryId() {
		return categoryId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public PetCategories(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}
 
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
 
	public PetCategories() {
	}
	public List<Pets> getPets() {
		return pets;
	}
	public void setPets(List<Pets> pets) {
		this.pets = pets;
		}
	@Override
	public String toString() {
		return "PetCategories [categoryId=" + categoryId + ", name=" + name + "]";
	}
 
    
}