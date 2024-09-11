package com.example.demo.service;
 
import java.util.List;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.entity.Transactions;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByIdException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.transactions.TransactionsNotFoundException;
 
 
public interface TransactionService {
	public List<Transactions> getAllTransactions() throws TransactionsNotFoundException;
	public List<Transactions> getCustomerTransactionsByCustomerId(int customerId) throws TransactionsNotFoundException,CustomerNotFoundException ;
	public List<Transactions> getCustomerTransactionByName(String customeName) throws TransactionsNotFoundException,CustomerNotFoundException ;
	public List<Transactions> getAllFailedTransactions() throws TransactionsNotFoundException;
	public List<Transactions> getAllsuccessfulTransactions() throws TransactionsNotFoundException;
	public Transactions createTransaction (TransactionDTO transactionDTO) throws CustomerNotFoundException,PetIdNotFoundException;
	public Transactions getTransactionById(int transactionId) throws TransactionsNotFoundException;
}
