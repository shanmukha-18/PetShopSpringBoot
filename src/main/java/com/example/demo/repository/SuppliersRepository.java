package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pets;
import com.example.demo.entity.Suppliers;


@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer>{
	// Find a supplier by their name
	Suppliers findByName(String name);

	// Find all suppliers with their associated address details
	@Query("SELECT s FROM Suppliers s LEFT JOIN s.address a")
	List<Suppliers> findAllWithDetails();
	
	

	

	// Find suppliers by their city
	 @Query("SELECT s FROM Suppliers s JOIN s.address a WHERE a.city = :city")
	 List<Suppliers> findByCity(@Param("city") String city);

	// Find suppliers by their state
	@Query("SELECT s FROM Suppliers s JOIN s.address a WHERE a.state = :state")
	List<Suppliers> findByState(@Param("state") String state);
	
	// Find suppliers by a list of names
    List<Suppliers> findByNameIn(List<String> names);

//	 @Query("DELETE FROM Suppliers s WHERE s.pet.petId = :petId")
//	 void deleteByPetId(int petId);
	}


