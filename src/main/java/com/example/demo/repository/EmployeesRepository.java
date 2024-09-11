package com.example.demo.repository;
 
import java.util.List;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employees;
 
@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer>{

 
	// Find employees by their first name
	List<Employees> findByFirstName(String firstName);


	// Find employees by their last name
	List<Employees> findByLastName(String lastName);

	// Find employees by their position
	List<Employees> findByPosition(String position);
 
}