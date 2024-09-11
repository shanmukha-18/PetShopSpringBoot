package com.example.demo.controllers;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.entity.Transactions;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.transactions.TransactionsNotFoundException;
import com.example.demo.service.TransactionServiceImpl;

import jakarta.validation.Valid;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server
@RequestMapping("/api/v1/transaction_history")
public class TransactionController {
 
	@Autowired
	TransactionServiceImpl transactionService;  // Service for transaction operations
	
	  
	// Retrieve all transactions and return HTTP status 200 OK
	@GetMapping("/get")
	public ResponseEntity<List<Transactions>>  getAllTransactions() throws TransactionsNotFoundException{
		return new ResponseEntity<List<Transactions>>(transactionService.getAllTransactions(),HttpStatus.OK);
	}
	
	// Retrieve a specific transaction by ID and return HTTP status 200 OK
	@GetMapping("/{transaction_id}")
	public ResponseEntity<Transactions> getTransactionById(@PathVariable("transaction_id") int transaction_id ) throws TransactionsNotFoundException{
		return new ResponseEntity<Transactions>(transactionService.getTransactionById(transaction_id), HttpStatus.OK);
	}
	
	// Retrieve transactions by customer ID and return HTTP status 200 OK
	@GetMapping("/by_customer/{customer_id}")
	public ResponseEntity<List<Transactions>>  getAllTransactionsByCustomerId(@PathVariable("customer_id") int customer_id)throws TransactionsNotFoundException,CustomerNotFoundException{
		return new ResponseEntity<List<Transactions>>(transactionService.getCustomerTransactionsByCustomerId(customer_id),HttpStatus.OK);
 
	}
	
	// Retrieve transactions by customer name and return HTTP status 200 OK
	  @GetMapping("/by_customer_name/{customerName}")
	    public ResponseEntity<List<Transactions>> getAllTransactionsByCustomerName(@PathVariable("customerName") String customerName) throws TransactionsNotFoundException,CustomerNotFoundException {
	        return new ResponseEntity<>(transactionService.getCustomerTransactionByName(customerName), HttpStatus.OK);
	    }
	  
	// Retrieve all successful transactions and return HTTP status 200 OK
	@GetMapping("/successful")
	public ResponseEntity<List<Transactions>>  getAllSuccessFullTransactions()throws TransactionsNotFoundException{
		return new ResponseEntity<List<Transactions>>(transactionService.getAllsuccessfulTransactions(),HttpStatus.OK);
 
	}
	
	// Retrieve all failed transactions and return HTTP status 200 OK
	@GetMapping("/failed")
	public ResponseEntity<List<Transactions>>  getAllFailedTransactions()throws TransactionsNotFoundException{
		return new ResponseEntity<List<Transactions>>(transactionService.getAllFailedTransactions(),HttpStatus.OK);
 
	}
	
	// Create a new transaction and return HTTP status 201 Created
	@PostMapping("/add")
	public ResponseEntity<Transactions>addTransaction(@Valid @RequestBody TransactionDTO transactionDTO)throws PetIdNotFoundException,CustomerNotFoundException{
		return new ResponseEntity<Transactions>(transactionService.createTransaction(transactionDTO),HttpStatus.CREATED);
 
	}
}
