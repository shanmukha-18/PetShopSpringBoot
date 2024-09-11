package com.example.demo.petShopTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.demo.entity.PetFood;
import com.example.demo.exceptionHandler.petFood.FoodInputInvalidException;
import com.example.demo.exceptionHandler.petFood.NoPetFoodFoundException;
import com.example.demo.exceptionHandler.petFood.PetFoodIdNotFoundException;
import com.example.demo.exceptionHandler.petFood.PetFoodNameNotFoundException;
import com.example.demo.repository.PetFoodRepository;
import com.example.demo.service.PetFoodServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class PetFoodServicesImplTest {
    @Mock
    private PetFoodRepository petFoodRepository; // Mocked repository for pet food
    
    @InjectMocks
    private PetFoodServiceImpl petFoodServices; // Service being tested
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);// Initialize mocks before each test
    }
    @Test
    public void testRetreiveAllPetFood() throws NoPetFoodFoundException {
        List<PetFood> petFoodList = new ArrayList<>();
        petFoodList.add(new PetFood(1, "Dog Food", "BrandA", "Dry", 10, 25.99));
        petFoodList.add(new PetFood(2, "Cat Food", "BrandB", "Wet", 15, 30.49));
     // Mock repository behavior to return a list of pet foods
        when(petFoodRepository.findAll()).thenReturn(petFoodList);
        List<PetFood> result = petFoodServices.retreiveAllPetFood();
        assertEquals(2, result.size()); 
     // Verify the size of the result list
        assertEquals("Dog Food", result.get(0).getName());
    }
    @Test
    public void testGetFoodById() throws PetFoodIdNotFoundException {
        PetFood petFood = new PetFood(1, "Dog Food", "BrandA", "Dry", 10, 25.99);// Mock repository behavior to return a pet food by ID
        when(petFoodRepository.findById(1)).thenReturn(Optional.of(petFood));
        PetFood result = petFoodServices.getFoodById(1);
        assertEquals("Dog Food", result.getName());// Verify the name of the retrieved pet food
    }
    @Test
    public void testGetPetFoodByName() throws PetFoodNameNotFoundException {
        List<PetFood> petFoodList = new ArrayList<>();
        petFoodList.add(new PetFood(1, "Dog Food", "BrandA", "Dry", 10, 25.99));
     // Mock repository behavior to return a list of pet foods by name
        when(petFoodRepository.findByName("Dog Food")).thenReturn(petFoodList);
        List<PetFood> result = petFoodServices.getPetFoodByName("Dog Food");
        assertEquals(1, result.size());// Verify the size of the result list
        assertEquals("Dog Food", result.get(0).getName());// Verify the name of the first item
    }
    @Test
    public void testAddFood() throws FoodInputInvalidException {
        PetFood petFood = new PetFood(1, "Dog Food", "BrandA", "Dry", 10, 25.99); 
     // Mock repository behavior to return the added pet food
        when(petFoodRepository.save(any(PetFood.class))).thenReturn(petFood);
        petFoodServices.addFood(petFood);
        verify(petFoodRepository, times(1)).save(petFood);// Ensure save method was called once
    }

    @Test
    public void testDeleteFood() throws PetFoodIdNotFoundException {
        PetFood petFood = new PetFood(1, "Dog Food", "BrandA", "Dry", 10, 25.99);
     // Mock repository behavior to return a pet food by ID
        when(petFoodRepository.findById(1)).thenReturn(Optional.of(petFood));
     // Mock repository behavior to do nothing when deleting by ID
        doNothing().when(petFoodRepository).deleteById(1);
        petFoodServices.deleteFood(1);
        verify(petFoodRepository, times(1)).deleteById(1); // Ensure deleteById method was called once
    }
}