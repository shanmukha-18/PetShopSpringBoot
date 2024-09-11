package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PetFood;


@Repository
public interface PetFoodRepository extends JpaRepository<PetFood, Integer>{
	// Find pet food by its type
	List<PetFood> findByType(String type);

	// Find pet food by its name
	List<PetFood> findByName(String name);

	// Find pet food by its brand
	List<PetFood> findByBrand(String brand);
	
	// Find pet food where the name is in the provided list of names
	List<PetFood> findByNameIn(List<String> names);
	 
	 
	 
}
