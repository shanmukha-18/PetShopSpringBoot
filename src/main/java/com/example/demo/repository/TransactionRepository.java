package com.example.demo.repository;
 
 
import java.util.List;
 
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transactions;
 
 
@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>{
 

	 // Retrieve a list of transactions for a specific customer by their customer ID
	@Query("SELECT t FROM Transactions t WHERE t.customer.customerId=:customer_id")
	List<Transactions> getCustomerTransactionsByCustomerId(@Param("customer_id") int customerId);
	
	// Retrieve a list of transactions for a customer by their first name
	@Query("SELECT t FROM Transactions t WHERE t.customer.firstName=:customerName")
	List<Transactions> getCustomerTransactionByName(@Param("customerName") String customerName);
 
	// Retrieve a list of transactions with a status of 'Failed'
	@Query("SELECT t FROM Transactions t WHERE t.transactionStatus=Failed")
	List<Transactions> getAllFailedTransactions();
 
	// Retrieve a list of transactions with a status of 'Success'
	@Query("SELECT t FROM Transactions t WHERE t.transactionStatus=Success")
	List<Transactions> getAllsuccessfulTransactions();
	
	// Retrieve a list of transactions for a specific pet by their pet ID
	@Query("SELECT t FROM Transactions t WHERE t.pet.petId = :petId")
    List<Transactions> findTransactionsOfPetByPetId(@Param("petId") int petId);
	
	// Delete transactions associated with a specific pet ID
	 @Query("DELETE FROM Transactions s WHERE s.pet.petId = :petId")
	 void deleteByPetId(int petId);
}
