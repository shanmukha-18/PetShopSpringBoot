package com.example.demo.repository;







import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PetCategories;

import jakarta.transaction.Transactional;
@Repository
public interface PetCategoriesRepository extends JpaRepository<PetCategories, Integer>{
	
	// Update the name of a pet category by its ID

    @Transactional
    @Modifying
    @Query("UPDATE PetCategories p SET p.name = :categoryName WHERE p.id = :categoryId")
    void updateCategoryNameById(int categoryId, String categoryName);

    // Find a pet category by its name
	PetCategories findByName(String name);
	
	// Find a pet category by its ID
    PetCategories findByCategoryId(int categoryId);




}
