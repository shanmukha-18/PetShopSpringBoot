package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pets;


@Repository
public interface PetsRepository extends JpaRepository<Pets, Integer>{
	// Find pets by the name of their category
	List<Pets> findByCategoryName(String category);
	
	// Find a pet by its ID
	Pets findByPetId(int petId);
	

	// Find a pet by its ID along with associated details like category, pet foods, vaccinations, and suppliers
	@Query("SELECT p FROM Pets p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.petFoods LEFT JOIN FETCH p.petVaccinations LEFT JOIN FETCH p.petSuppliers WHERE p.petId = :petId")
	Pets findByPetIdWithDetails(@Param("petId") int petId);

	// Find all pets with their associated details like category, pet foods, vaccinations, and suppliers
	@Query("SELECT p FROM Pets p LEFT JOIN p.category LEFT JOIN p.petFoods LEFT JOIN p.petVaccinations LEFT JOIN p.petSuppliers")
	List<Pets> findAllWithDetails();
	 
	// Find pets by their breed
	@Query("SELECT p FROM Pets p WHERE p.breed = :breed")
	 List<Pets> findByBreed(String breed);
	 
	  @Query("SELECT p FROM Pets p WHERE p.name IN :names")
	    List<Pets> findByNameIn(@Param("names") List<String> names);



}
