package com.example.demo.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pet_food")
@JsonIgnoreProperties({"pets"})
public class PetFood {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int foodId;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(min=3 ,message = "Name must be atleast 3 characters")
    private String name;
 
    @Column(name = "brand")
    @Size(min=3, message = "Brand must be atleast 3 characters")
    private String brand;
 
    @Column(name = "type", length = 50)
    private String type;
 
    @Column(name = "quantity")
    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;
  
    
    @ManyToMany(mappedBy = "petFoods")
    @JsonBackReference
    private List<Pets> pets;
    
 
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getFoodId() {

		return foodId;

	}
 
	public String getName() {

		return name;

	}
 
	public void setName(String name) {

		this.name = name;

	}
 
	public String getBrand() {

		return brand;

	}
 
	public void setBrand(String brand) {

		this.brand = brand;

	}
 
	public String getType() {

		return type;

	}
 
	public void setType(String type) {

		this.type = type;

	}
 
	public Integer getQuantity() {

		return quantity;

	}
 
	public void setQuantity(Integer quantity) {

		this.quantity = quantity;

	}
 
	public Double getPrice() {

		return price;

	}
 
	public void setPrice(Double price) {

		this.price = price;

	}

	public PetFood(int foodId, String name, String brand, String type, Integer quantity, Double price) {

		this.foodId = foodId;

		this.name = name;

		this.brand = brand;

		this.type = type;

		this.quantity = quantity;

		this.price = price;

	}
 
	public PetFood(String name, String brand, String type, Integer quantity, Double price) {

		this.name = name;

		this.brand = brand;

		this.type = type;

		this.quantity = quantity;

		this.price = price;

	}
 
	public PetFood() {

	}
 
	@Override

	public String toString() {

		return "PetFood [foodId=" + foodId + ", name=" + name + ", brand=" + brand + ", type=" + type + ", quantity="

				+ quantity + ", price=" + price + "]";

	}
 
   

}
 
 