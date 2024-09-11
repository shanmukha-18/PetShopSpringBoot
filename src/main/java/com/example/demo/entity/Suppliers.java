package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "suppliers")
@JsonIgnoreProperties({"pets"})
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(min= 3, message = "Name must be atleast 3 characters")
    private String name;

    @Column(name = "contact_person")
    @Size(min=3,message = "Contact person must be greater than 3 characters")
    private String contactPerson;

    
    @Column(name = "phone_number")
    @NotBlank(message = "Phone number is mandatory")

    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonBackReference
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @NotNull(message = "Address is mandatory")
    private Addresses address;

    
    @ManyToMany(mappedBy = "petSuppliers",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Pets> pets;

    // Constructors
    public Suppliers() {}

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

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

	public Suppliers(int supplierId,
			@NotBlank(message = "Name is mandatory") @Size(min = 6, message = "Name must be greater than 6 characters") String name,
			@Size(min = 6, message = "Contact person must be greater than 6 characters") String contactPerson,
			@NotBlank(message = "Phone number is mandatory") String phoneNumber,
			@Email(message = "Email should be valid") String email,
			@NotNull(message = "Address is mandatory") Addresses address, List<Pets> pets) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "Suppliers [supplierId=" + supplierId + ", name=" + name + ", contactPerson=" + contactPerson
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", pets=" + pets + "]";
	}

    
}
