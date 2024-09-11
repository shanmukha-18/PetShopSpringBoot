package com.example.demo.entity;



import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vaccinations")
public class Vaccinations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private int vaccinationId;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, message = "Name must be atleast 3 characters")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price",columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;

    @Column(name = "available",columnDefinition = "tinyint")
    @NotNull(message = "Availability is mandatory")
    @PositiveOrZero(message = "Available must be zero or positive")
    private int available;
    
    
    @ManyToMany(mappedBy = "petVaccinations",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Pets> pets;
    

	public int getVaccinationId() {
		return vaccinationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

	public int getAvailable() {
		return available;
	}

	public void setVaccinationId(int vaccinationId) {
		this.vaccinationId = vaccinationId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int isAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Vaccinations(int vaccinationId, String name, String description, Double price, int available) {
		super();
		this.vaccinationId = vaccinationId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	public Vaccinations(String name, String description, Double price, int available) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	public Vaccinations() {
		super();
	}

	public Vaccinations(int vaccinationId2, String name2, String description2, int i, boolean b) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vaccinations [vaccinationId=" + vaccinationId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", available=" + available + "]";
	}

    
}

