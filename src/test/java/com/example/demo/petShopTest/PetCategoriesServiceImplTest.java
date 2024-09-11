package com.example.demo.petShopTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.PetCategoriesDTO;
import com.example.demo.entity.PetCategories;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesInputInvalidException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByIdException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundByNameException;
import com.example.demo.exceptionHandler.petCategories.PetCategoriesNotFoundException;
import com.example.demo.repository.PetCategoriesRepository;
import com.example.demo.service.PetCategoriesServiceImpl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class PetCategoriesServiceImplTest {
    @Mock
    private PetCategoriesRepository petCategoriesRepository; // Mocked repository for pet categories
    @InjectMocks
    private PetCategoriesServiceImpl petCategoriesService; // Service being tested
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }
    @Test
    public void testGetAllPetCategories() throws PetCategoriesNotFoundException {
    	//Setup
        List<PetCategories> petCategoriesList = new ArrayList<>();
        petCategoriesList.add(new PetCategories(1, "Cats"));
        petCategoriesList.add(new PetCategories(2, "Dogs"));
     // Mock repository behavior to return a list of pet categories
        when(petCategoriesRepository.findAll()).thenReturn(petCategoriesList);
        List<PetCategories> result = petCategoriesService.getAllPetCategories();
        assertEquals(2, result.size()); // Verify the size of the result list
        assertEquals("Cats", result.get(0).getName()); // Verify the name of the first category
    }
    @Test
    public void testGetPetCategoriesById() throws PetCategoriesNotFoundByIdException {
        PetCategories petCategory = new PetCategories(1, "Cats");
     // Mock repository behavior to return a pet category by ID
        
        when(petCategoriesRepository.findById(1)).thenReturn(Optional.of(petCategory));
        PetCategories result = petCategoriesService.getPetCategoriesById(1);
        assertEquals("Cats", result.getName());  
     // Verify the name of the retrieved category
    }
    @Test
    public void testGetCategoriesByName() throws PetCategoriesNotFoundByNameException {
        PetCategories petCategoriesList = new PetCategories(1, "Cats");
     // Mock repository behavior to return a pet category by name
//        petCategoriesList.add(new PetCategories(1, "Cats"));
        when(petCategoriesRepository.findByName("Cats")).thenReturn(petCategoriesList);
        PetCategories result = petCategoriesService.getCategoriesByName("Cats");
        assertEquals(1, result.getCategoryId());  
     // Verify the ID of the retrieved category
        assertEquals("Cats", result.getName());// Verify the name of the retrieved category
    }
    @Test
    public void testAddCategories() throws PetCategoriesInputInvalidException {
        PetCategoriesDTO dto = new PetCategoriesDTO();
        dto.setName("Cats");
        PetCategories petCategory = new PetCategories();
        petCategory.setName("Cats");
        
     // Mock repository behavior to return the added category
        when(petCategoriesRepository.save(any(PetCategories.class))).thenReturn(petCategory);
        petCategoriesService.addCategories(dto);
        verify(petCategoriesRepository, times(1)).save(any(PetCategories.class));
        
     // Ensure save method was called once
    }

    @Test
    public void testDeletePetCategory() throws PetCategoriesNotFoundByIdException {
        PetCategories petCategory = new PetCategories(1, "Cats");
        // Mock repository behavior to return a pet category by ID
        when(petCategoriesRepository.findById(1)).thenReturn(Optional.of(petCategory));
     // Mock repository behavior to do nothing when deleting a pet category
        doNothing().when(petCategoriesRepository).delete(any(PetCategories.class));
        petCategoriesService.deletePetCategory(1);
        verify(petCategoriesRepository, times(1)).delete(petCategory);
        
  
    }
}