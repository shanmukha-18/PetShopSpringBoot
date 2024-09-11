package com.example.demo.petShopTest;


	import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
	import static org.junit.jupiter.api.Assertions.assertThrows;
	import static org.mockito.Mockito.when;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.NoSuchElementException;
	import java.util.Optional;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.jupiter.api.extension.ExtendWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Pets;
import com.example.demo.exceptionHandler.pets.PetCategoryNotFoundException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.pets.PetsNotFoundException;
import com.example.demo.repository.PetCategoriesRepository;
	import com.example.demo.repository.PetsRepository;
	import com.example.demo.service.PetServiceImpl;
   @ExtendWith(MockitoExtension.class)
	public class PetServiceImplTest {
	   
		@Mock
	    private PetsRepository petsRepository; // Mocked repository for Pets and PetCategories
	    @Mock
	    private PetCategoriesRepository petCategoriesRepository;
	    @InjectMocks
	    private PetServiceImpl petService;
	   

	    @Test
	    public void testRetrieveById() {
	        Pets pet = new Pets();
	        //pet.setId(1);
	        Optional<Pets> optionalPet = Optional.of(pet);
	        
	     // Mock Pet object wrapped in Optional
	     // Mock repository behavior to return a pet by ID
	        when(petsRepository.findById(1)).thenReturn(optionalPet);
	        assertDoesNotThrow(() -> petService.getPetsById(1));
	    }
	    @Test
	    public void testRetrieveById_PetIdNotFoundException() {
	    	// Mock repository to throw NoSuchElementException  by pet ID
	        when(petsRepository.findById(1)).thenThrow(NoSuchElementException.class);
	        assertThrows(PetIdNotFoundException.class, () -> petService.getPetsById(1));
	    }
	    @Test
	    public void testGetPetsByCategory() {
	        List<Pets> petsList = new ArrayList<>();
	        petsList.add(new Pets());
	     // Adding a mock Pet object to the list
	        
	        // Mock repository behavior to return a list of pets by category
	        when(petsRepository.findByCategoryName("Category")).thenReturn(petsList);
	    
	    }
	    @Test
	    public void testGetPetsByCategory_PetCategoryNotFoundException() {
	    	 
	    	// Mock repository to return an empty list indicating no pets found for the given category
	        when(petsRepository.findByCategoryName("Category")).thenReturn(new ArrayList<>());
	        assertThrows(PetCategoryNotFoundException.class, () -> petService.getPetsByCategory("Category"));
	     
	    }
	    }


	