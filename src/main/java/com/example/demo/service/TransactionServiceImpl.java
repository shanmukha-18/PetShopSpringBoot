package com.example.demo.service;
 
 
import java.util.List;
import java.util.Optional;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Pets;
import com.example.demo.entity.Transactions;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByIdException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.transactions.TransactionsNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PetsRepository;
import com.example.demo.repository.TransactionRepository;
 
 
@Service
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	TransactionRepository transactionRepository;
 
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PetsRepository petsRepository;
	
	 
	// Fetch all transactions from the repository.
	@Override
	public List<Transactions> getAllTransactions() throws TransactionsNotFoundException{
		// TODO Auto-generated method stub
		List<Transactions> lTransactions=transactionRepository.findAll();
		if(lTransactions.size()==0) {
			throw new TransactionsNotFoundException("No transations in database");
		}else {
			return lTransactions;
		}
	}
 
	// Fetch transactions for the given customer ID.
	@Override
	public List<Transactions> getCustomerTransactionsByCustomerId(int customerId) throws TransactionsNotFoundException,CustomerNotFoundException{
		Customers customer=customerRepository.getCustomerById(customerId);
		if(customer==null) {
			throw new CustomerNotFoundException("There is no customer with this id");
		}else {
			List<Transactions> lTransactions=transactionRepository.getCustomerTransactionsByCustomerId(customerId);
			if(lTransactions.size()==0) {
				throw new TransactionsNotFoundException("No transactions in database on this customer id till now");
			}else {
				return lTransactions;
			}
		}
	}
 
	 
	// Fetch all failed transactions.
	@Override
	public List<Transactions> getAllFailedTransactions() throws TransactionsNotFoundException{
		List<Transactions> lTransactions=transactionRepository.getAllFailedTransactions();
		if(lTransactions.size()==0) {
			throw new TransactionsNotFoundException("No cancelled transations in database");
		}else {
			return lTransactions;
		}
	}
	   
	// Fetch all successful transactions.
	@Override
	public List<Transactions> getAllsuccessfulTransactions() throws TransactionsNotFoundException{
		List<Transactions> lTransactions=transactionRepository.getAllsuccessfulTransactions();
		if(lTransactions.size()==0) {
			throw new TransactionsNotFoundException("No successfull transations in database");
		}else {
			return lTransactions;
		}
	}

	   
	// Create a new Transactions
	@Override
	public Transactions createTransaction(TransactionDTO transactionDTO) throws CustomerNotFoundException,PetIdNotFoundException {
//		// TODO Auto-generated method stub
//		 Customers customer = customerRepository.findById(transactionDTO.getCustomerId()).get();
//;
		
		Optional<Customers> optionalCustomer = customerRepository.findById(transactionDTO.getCustomerId());
		if (!optionalCustomer.isPresent()) {
		    throw new CustomerNotFoundException("Customer not found with given id " + transactionDTO.getCustomerId());
		}
		Customers customer = optionalCustomer.get();

		Optional<Pets> optionalPet = petsRepository.findById(transactionDTO.getPetId());
		if (!optionalPet.isPresent()) {
		    throw new PetIdNotFoundException("Pet not found with given id " + transactionDTO.getPetId());
		}
		Pets pet = optionalPet.get();

		Transactions transaction = new Transactions();
		transaction.setCustomer(customer);
		transaction.setPet(pet);
		transaction.setTransactionDate(transactionDTO.getTransactionDate());
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setTransactionStatus(transactionDTO.getTransactionStatus());

		try {
		    return transactionRepository.save(transaction);
		} catch (Exception e) {
		    // Log or handle the exception
		    throw new RuntimeException("Failed to save transaction", e);
		}

	}
//Update transaction
	@Override
	public List<Transactions> getCustomerTransactionByName(String customeName)
			throws TransactionsNotFoundException, CustomerNotFoundException {
		Customers customer=customerRepository.getCustomerByName(customeName);
		if(customer==null) {
			throw new CustomerNotFoundException("There is no customer with this name");
		}else {
			List<Transactions> lTransactions=transactionRepository.getCustomerTransactionByName(customeName);
			if(lTransactions.size()==0) {
				throw new TransactionsNotFoundException("No transactions in database on this customer name till now");
			}else {
				return lTransactions;
			}
		}
	}
	   
	// Fetch the transaction by its ID.
	@Override
	public Transactions getTransactionById(int transactionId) throws TransactionsNotFoundException {
		return transactionRepository.findById(transactionId).get();
	}
	
}
 