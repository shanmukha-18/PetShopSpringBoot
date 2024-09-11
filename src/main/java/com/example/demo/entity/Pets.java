package com.example.demo.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pets")
//@JsonIgnoreProperties({"petSuppliers"})
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private int petId;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name must be atleast 3 characters")
    private String name;

    @Column(name = "breed")
    @NotBlank(message = "Breed is mandatory")
    @Size(min=3, message = "Breed must be atleast 3 characters")
    private String breed;

    @Column(name = "age")
    @Min(value = 0, message = "Age must be a positive integer")
    private int age;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be a positive number")
    private int price;

    public Pets() {
	}
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//	@JsonManagedReference
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private PetCategories category;
	
	
	
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl;
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
        name = "pet_petfood",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
   
    private List<PetFood> petFoods;
    
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) 
//    @JsonManagedReference
    @JoinTable(
        name = "pet_vaccinations",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "vaccination_id")
    )
    private List<Vaccinations> petVaccinations;
    
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
        name = "pet_suppliers",
        joinColumns = @JoinColumn(name = "pet_id"),
        inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )

    private List<Suppliers> petSuppliers;

    

	public int getPetId() {
		return petId;
	}



	public void setPetId(int petId) {
		this.petId = petId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBreed() {
		return breed;
	}



	public void setBreed(String breed) {
		this.breed = breed;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public PetCategories getCategory() {
		return category;
	}



	public void setCategory(PetCategories category) {
		this.category = category;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public List<PetFood> getPetFoods() {
		return petFoods;
	}



	public void setPetFoods(List<PetFood> petFoods) {
		this.petFoods = petFoods;
	}



	public List<Vaccinations> getPetVaccinations() {
		return petVaccinations;
	}



	public void setPetVaccinations(List<Vaccinations> petVaccinations) {
		this.petVaccinations = petVaccinations;
	}



	public List<Suppliers> getPetSuppliers() {
		return petSuppliers;
	}



	public void setPetSuppliers(List<Suppliers> petSuppliers) {
		this.petSuppliers = petSuppliers;
	}



	@Override
	public String toString() {
		return "Pets [petId=" + petId + ", name=" + name + ", breed=" + breed + ", age=" + age + ", price=" + price
				+ ", category=" + category + ", description=" + description + ", imageUrl=" + imageUrl + "]";
	}

}