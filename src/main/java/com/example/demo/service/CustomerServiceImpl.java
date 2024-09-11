package com.example.demo.service;
 
 
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Addresses;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Transactions;
import com.example.demo.enums.transaction_status;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomersNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.NoTransactionsFoundByCustomerId;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PetsRepository;
import com.example.demo.repository.TransactionRepository;

import jakarta.transaction.Transactional;
 
 
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
 
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PetsRepository petsRepository;
	
	@Autowired
	AddressRepository addressRepository;
 
	@Transactional
	public ApiResponse createCustomer(CustomerDTO customerDTO) {

		 
		// Fetch address from the repository using address ID from customerDTO
			Addresses address = addressRepository.findById(customerDTO.getAddressId()).get();
			


	        
	        // Create and populate the Customer entity
	        Customers customer = new Customers();
	        customer.setFirstName(customerDTO.getFirstName());
	        customer.setLastName(customerDTO.getLastName());
	        customer.setEmail(customerDTO.getEmail());
	        customer.setPhoneNumber(customerDTO.getPhoneNumber());
	        customer.setAddress(address);
	        customerRepository.save(customer);
	        return new ApiResponse(LocalDate.now(),"Customer created successfully");
	}
		
	// Update existing customer details
	@Transactional
	public ApiResponse updateCustomer(int customer_id,CustomerDTO customer) throws CustomerNotFoundException {
		Customers existingCustomer = customerRepository.getCustomerById(customer_id);
		Addresses address = addressRepository.findById(customer.getAddressId()).get();
		if(existingCustomer==null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		else {
			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setPhoneNumber(customer.getPhoneNumber());
			existingCustomer.setAddress(address);
			customerRepository.save(existingCustomer);
			return new ApiResponse(LocalDate.now(),"Customer updated successfully");
		}
	}

	// Delete customer from repository
	@Transactional
	public ApiResponse deleteCustomerById(int customer_id) throws CustomerNotFoundException{
		Customers customer=customerRepository.getCustomerById(customer_id);
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found");
		}else {
			customerRepository.deleteById(customer_id);
			return new ApiResponse(LocalDate.now(),"Customer deleted successfully");
		}
	}
	
	// Retrieve all customers from the repository
	public List<Customers> getAllCustomers()throws CustomersNotFoundException{
		List<Customers> cList=customerRepository.findAll();
		if(cList.size()==0) {
			throw new CustomersNotFoundException("No customers in the database");
		}else {
			return cList;
		}
	}
	
	
	// Retrieve customer by ID
	public Customers getCustomerById(int customer_id)  throws CustomerNotFoundException{
		Customers customer = customerRepository.getCustomerById(customer_id);
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found");
		}else {
			return customer;
		}
	}
	
	// Retrieve customers by city
	public List<Customers> getCustomerBycity(String city) throws CustomersNotFoundException{
		List<Customers> customers=customerRepository.findByCity(city);
		if(customers.size()==0) {
			throw new CustomersNotFoundException("There are no customers with this city in the database");
		}else {
			return customers;
		}
	}
 
	
	// Retrieve customers by state
	public List<Customers> findByState(String state)throws CustomersNotFoundException{
		List<Customers> customers=customerRepository.findByState(state);
		if(customers.size()==0) {
			throw new CustomersNotFoundException("There are no customers with this State in the database");
		}else {
			return customers;
		}
	}
 
	
	// Retrieve transactions by customer ID
	public List<Transactions> findByCustomerId(int customer_id)throws NoTransactionsFoundByCustomerId{
		List<Transactions> transactions=customerRepository.findByCustomerId(customer_id);
		if(transactions.size()==0) {
			throw new NoTransactionsFoundByCustomerId("No transactions with this customer id");
		}else {
			return transactions;
		}
	}
	
	// Retrieve customers based on transaction status
    public List<Customers> findCustomersByTransactionStatus(transaction_status status){
		return customerRepository.findCustomersByTransactionStatus(status);
    }
 
	
 // Retrieve customers who have no transactions
	public List<Customers> findCustomerByNotransaction(){
		return customerRepository.findCustomerByNotransaction();
	}
	
	// Retrieve customer by first name and last name
    public Customers findCustomerByfirstNameAndlastName(String firstName,String lastName)throws CustomersNotFoundException{
    	Customers customer=customerRepository.findCustomerByfirstNameAndlastName(firstName,lastName);
    	if(customer==null) {
    		throw new CustomersNotFoundException("Customer not found with this "+firstName+" and "+lastName);
    	}else {
    		return customer;
    	}
    }
}
 



