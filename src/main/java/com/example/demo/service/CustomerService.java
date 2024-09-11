package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Transactions;
import com.example.demo.enums.transaction_status;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomersNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.NoTransactionsFoundByCustomerId;

public interface CustomerService {
	public ApiResponse createCustomer(CustomerDTO customerDTO);
	public ApiResponse updateCustomer(int customer_id,CustomerDTO customer) throws CustomerNotFoundException;
	public ApiResponse deleteCustomerById(int customer_id) throws CustomerNotFoundException;
	public List<Customers> getAllCustomers()throws CustomersNotFoundException;
	public Customers getCustomerById(int customer_id)  throws CustomerNotFoundException;
	public List<Customers> getCustomerBycity(String city) throws CustomersNotFoundException;
	public List<Customers> findByState(String state)throws CustomersNotFoundException;
	public List<Transactions> findByCustomerId(int customer_id)throws NoTransactionsFoundByCustomerId;
	public List<Customers> findCustomersByTransactionStatus(transaction_status status);
	public Customers findCustomerByfirstNameAndlastName(String firstName,String lastName)throws CustomersNotFoundException;
   
}
