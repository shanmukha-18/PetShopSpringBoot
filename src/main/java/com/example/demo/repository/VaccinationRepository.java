package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Vaccinations;


@Repository
public interface VaccinationRepository extends JpaRepository<Vaccinations, Integer>{
	// Find all vaccinations that are currently available
	@Query("select v from Vaccinations v where v.available=1")
	List<Vaccinations> findByAvailable();
	
	// Find all vaccinations that are currently unavailable
	@Query("select v from Vaccinations v where v.available=0")
	List<Vaccinations> findByUnavailable();
	
	
	// Find vaccinations where the name is in the provided list of names
	  List<Vaccinations> findByNameIn(List<String> names);

	  //	@Query("DELETE FROM Vaccinations v WHERE v.pet.petId = :petId")
//	 void deleteByPetId(int petId);
}
