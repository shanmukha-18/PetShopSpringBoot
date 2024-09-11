package com.example.demo.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"pets","customers"})
public class Addresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "street")
	@NotBlank(message = "Street name is mandatory")
	 @Size(min = 3, message = "Street name must be atleast 3 characters")
	private String street;

	@Column(name = "city")
	@NotBlank(message = "City name is mandatory")
	 @Size(min = 3, message = "City name must be greater than 3 characters")
	private String city;

	@Column(name = "state")
	@NotBlank(message = "State name is mandatory")
	 @Size(min=3, message = "State name must be atleast 3  characters")
	private String state;

	@Column(name = "zip_code")
    @NotBlank(message = "Zip code is mandatory")
    @Size(min=3, message = "Zip code must be atleast 3 characters")
    @Pattern(regexp = "^[0-9]{5}(-[0-9]{4})?$", message = "Zip code must be in the format 12345 or 12345-6789")

	private String zipCode;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true) 
//	@JsonManagedReference
	private List<Suppliers> suppliers;
//	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true) 
//	@JsonManagedReference
	private List<Customers> customers;


	public int getAddressId() {
		return addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Addresses(String street, String city, String state, String zipCode) {

		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Addresses() {

	}

	@Override
	public String toString() {
		return "Addresses [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}

}
