package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Addresses;
import java.util.List;



@Repository
public interface AddressRepository extends JpaRepository<Addresses, Integer>{
	  @Query("SELECT a FROM Addresses a WHERE a.city = :city")
	    Addresses findByCity(@Param("city") String city);
}
