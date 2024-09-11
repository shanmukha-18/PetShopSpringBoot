package com.example.demo.controllers;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Transactions;
import com.example.demo.enums.transaction_status;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomersNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.NoTransactionsFoundByCustomerId;
import com.example.demo.service.CustomerServiceImpl;
 
import jakarta.validation.Valid;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server
@RequestMapping("/api/v1/customers")  // Base path for customer-related endpoints
public class CustomerController {
	@Autowired
	CustomerServiceImpl customerService;  // Service layer for customer operations
	  
	// Create a new customer and return HTTP status 201 Created
	@PostMapping("/add")
	public ResponseEntity<ApiResponse>createCustomer(@Valid @RequestBody CustomerDTO customer){
		return new ResponseEntity<ApiResponse>(customerService.createCustomer(customer),HttpStatus.CREATED);
	}
	
	// Update an existing customer by ID and return HTTP status 200 OK
	@PutMapping("/update/{customer_id}")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("customer_id")int customer_id,@Valid @RequestBody CustomerDTO customer)throws CustomerNotFoundException{
		return new ResponseEntity<ApiResponse>(customerService.updateCustomer(customer_id,customer),HttpStatus.OK);
	}
	
	// Delete a customer by ID and return HTTP status 200 OK
	@DeleteMapping("/delete/{customer_id}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customer_id")int customer_id)throws CustomerNotFoundException{
		return new ResponseEntity<ApiResponse>(customerService.deleteCustomerById(customer_id),HttpStatus.OK);
	}
	
	 
	// Retrieve all customers and return HTTP status 200 OK
	@GetMapping("/get")
	public ResponseEntity<List<Customers>> getAllCustomers() throws CustomersNotFoundException {
		return new ResponseEntity<List<Customers>>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	// Retrieve a customer by ID and return HTTP status 200 OK
	@GetMapping("/{customer_id}")
	public ResponseEntity<Customers>  getCustomerById(@PathVariable("customer_id") int customer_id)  throws CustomerNotFoundException{
		Customers customer=customerService.getCustomerById(customer_id);
		return new ResponseEntity<Customers>(customer,HttpStatus.OK);
	}
	
	// Retrieve customers by city and return HTTP status 200 OK
	@GetMapping("/by_city/{city}")
	public ResponseEntity<List<Customers>>  getCustomersByCity(@PathVariable("city") String city)throws CustomersNotFoundException{
		return new ResponseEntity<List<Customers>>(customerService.getCustomerBycity(city),HttpStatus.OK);
	}
	
	 
	// Retrieve customers by state and return HTTP status 200 OK
	@GetMapping("/by_state/{state}")
	public ResponseEntity<List<Customers>>  findByState(@PathVariable("state") String state)throws CustomersNotFoundException{
		return new ResponseEntity<List<Customers>>(customerService.findByState(state),HttpStatus.OK);
 
	}
	
	// Retrieve transactions for a customer by ID and return HTTP status 200 OK
	@GetMapping("/transactions/{customer_id}")
	public ResponseEntity<List<Transactions>>  findByCustomerId(@PathVariable("customer_id") int customer_id)throws NoTransactionsFoundByCustomerId{
		return new ResponseEntity<List<Transactions>>(customerService.findByCustomerId(customer_id),HttpStatus.OK);
 
	}
	
	 
	// Retrieve customers by transaction status and return HTTP status 200 OK
	@GetMapping("/transactions_status/{status}")
	public ResponseEntity<List<Customers>>  findByTransactionStatus(@PathVariable("status") transaction_status status){
		return new ResponseEntity<List<Customers>>(customerService.findCustomersByTransactionStatus(status),HttpStatus.OK);
 
	}
	
	// Retrieve customers with no transactions and return HTTP status 200 OK
	@GetMapping("/no-transactions")
	public ResponseEntity<List<Customers>>  findByTransactionStatus(){
		return new ResponseEntity<List<Customers>>(customerService.findCustomerByNotransaction(),HttpStatus.OK);
 
	}
	
	// Retrieve a customer by first and last name and return HTTP status 200 OK
	@GetMapping("/name/{first_name}/{last_name}")
	public ResponseEntity<Customers>  findByCustomerByFirstNameAndLastName(@PathVariable("first_name") String first_name,@PathVariable("last_name") String last_name)throws CustomersNotFoundException{
		return new ResponseEntity<Customers>(customerService.findCustomerByfirstNameAndlastName(first_name,last_name),HttpStatus.OK);
 
	}
}
