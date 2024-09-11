package com.example.demo.repository;
 
import java.util.List;
 
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customers;
import com.example.demo.entity.Transactions;
import com.example.demo.enums.transaction_status;
 
 
@Repository
 
public interface CustomerRepository extends JpaRepository<Customers, Integer>{

 	// Retrieve a customer by their unique customer ID
		@Query("SELECT c FROM Customers c WHERE c.customerId=:customer_id")
		Customers getCustomerById(@Param("customer_id") int customer_id);
		
		
	// Retrieve a customer by their first name (assumes uniqueness)
		@Query("SELECT c FROM Customers c WHERE c.firstName=:customerName")
		Customers getCustomerByName(@Param("customerName") String customerName);
 
		// Retrieve a list of customers based on their city
		@Query("SELECT c FROM Customers c JOIN c.address address WHERE address.city = :city")
		List<Customers> findByCity(@Param("city") String city);

		// Retrieve a list of customers based on their state
		@Query("SELECT c FROM Customers c JOIN c.address address WHERE address.state = :state")
		List<Customers> findByState(@Param("state") String state);
 

		// Retrieve a list of transactions for a specific customer by customer ID
		@Query("SELECT t FROM Transactions t WHERE t.customer.customerId = :customer_id")
		List<Transactions> findByCustomerId(@Param("customer_id") int customer_id);
 
		// Retrieve a list of customers with a specific transaction status
		@Query("SELECT DISTINCT t.customer FROM Transactions t WHERE t.transactionStatus = :status")
	    List<Customers> findCustomersByTransactionStatus(@Param("status") transaction_status status);
		
		// Retrieve a list of customers who do not have any transactions
		@Query("SELECT c FROM Customers c WHERE c NOT IN (SELECT t.customer FROM Transactions t)")
		List<Customers> findCustomerByNotransaction();
 
		 // Retrieve a customer by their first and last names (assumes uniqueness)
		@Query("SELECT c FROM Customers c WHERE c.firstName = :firstName AND c.lastName = :lastName")
	    Customers findCustomerByfirstNameAndlastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}


