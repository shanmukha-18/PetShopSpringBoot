package com.example.demo.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SupplierDTO {

    private int supplierId;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name must be atleast 3 characters")
    private String name;

    @Size(min = 3, message = "Contact person must be greater than 3 characters")
    private String contactPerson;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Address ID is mandatory")
    private int addressId;
//    private String city;

    // Optional: If you want to include the list of pets in the DTO
    private List<Integer> petIds;
//    private List<String> petNames;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public List<Integer> getPetIds() {
		return petIds;
	}

	public void setPetIds(List<Integer> petIds) {
		this.petIds = petIds;
	}


  
	

  
}
