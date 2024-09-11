package com.example.demo.entity;
 
import java.util.Date;
 
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "employees")
public class Employees {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
 
    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "Name is mandatory")
    @Size(min= 3,message = "Name must be greater than 3 characters")
    private String firstName;
 
    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Name is mandatory")
    @Size(min= 3,message = "Name must be greater than 3 characters")
    private String lastName;

 
    @Column(name = "position", length = 50)
    private String position;

 
    @Column(name = "hire_date",columnDefinition = "Date")
    private Date hireDate;

 
    @Column(name = "phone_number", length = 20)
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
 
    @Column(name = "email", length = 100)
    @Email(message = "Email should be valid")
    private String email;
 
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Addresses address;
 
	public int getEmployeeId() {
		return employeeId;
	}
 
	public String getFirstName() {
		return firstName;
	}
 
	public String getLastName() {
		return lastName;
	}
 
	public String getPosition() {
		return position;
	}
 
	public Date getHireDate() {
		return hireDate;
	}
 
	public String getPhoneNumber() {
		return phoneNumber;
	}
 
	public String getEmail() {
		return email;
	}
 
	public Addresses getAddress() {
		return address;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
	public void setPosition(String position) {
		this.position = position;
	}
 
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
 
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public void setAddress(Addresses address) {
		this.address = address;
	}
 
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", position=" + position + ", hireDate=" + hireDate + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", address=" + address + "]";
	}
 
	public Employees(int employeeId, String firstName, String lastName, String position, Date hireDate,
			String phoneNumber, String email, Addresses address) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.hireDate = hireDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}
 
	public Employees() {
	}

}
 