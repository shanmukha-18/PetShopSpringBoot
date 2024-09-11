package com.example.demo.petShopTest;

import com.example.demo.entity.Addresses;
import com.example.demo.exceptionHandler.address.AddressInputInvalidException;
import com.example.demo.exceptionHandler.address.AddressNotFoundByIdException;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
import com.example.demo.repository.AddressRepository;

import com.example.demo.service.AddressServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
 
@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
 
    @Mock
    private AddressRepository addressRepo; // Mocked AddressRepository to simulate database interactions
 
    @InjectMocks
    private AddressServiceImpl addressService; // The service being tested
 
    private Addresses address; // Sample address for testing
 
    @BeforeEach
    void setUp() {
    	// Initialize the address object with sample data before each test
        address = new Addresses();
       
        address.setCity("Sample City");
        address.setState("Sample State");
        address.setStreet("123 Sample Street");
        address.setZipCode("12345");
    }
 
    @Test
    void testGetAllAddresses() throws AddressNotFoundException {
        // Mock repository behavior
        when(addressRepo.findAll()).thenReturn(Arrays.asList(address));
 
        // Test service method
        assertEquals(1, addressService.getAllAddresses().size());
    }
 
    @Test
    void testGetAllAddressesNoAddresses() {
        // Mock repository behavior
        when(addressRepo.findAll()).thenReturn(Arrays.asList());
 
        // Test service method
        assertThrows(AddressNotFoundException.class, () -> {
            addressService.getAllAddresses();
        });
    }
 
    @Test
    void testGetAddressById() throws AddressNotFoundByIdException {
        // Mock repository behavior
        when(addressRepo.findById(1)).thenReturn(Optional.of(address));
 
        // Test service method
        assertEquals(address, addressService.getAddressById(1));
    }
 
    @Test
    void testGetAddressByIdNotFound() {
        // Mock repository behavior
        when(addressRepo.findById(1)).thenReturn(Optional.empty());
 
        // Test service method
        assertThrows(AddressNotFoundByIdException.class, () -> {
            addressService.getAddressById(1);
        });
    }
 
    @Test
    void testAddAddress() throws AddressInputInvalidException {
        // Mock repository behavior
        when(addressRepo.save(address)).thenReturn(address);
 
        // Test service method
        addressService.addAddress(address);
    }
 
    @Test
    void testAddAddressFailure() {
        // Mock repository behavior
        when(addressRepo.save(address)).thenThrow(new RuntimeException("Database error"));
 
        // Test service method
        assertThrows(AddressInputInvalidException.class, () -> {
            addressService.addAddress(address);
        });
    }
 
    @Test
    void testUpdateAddress() throws AddressNotFoundByIdException {
        // Mock repository behavior
        when(addressRepo.findById(1)).thenReturn(Optional.of(address));
     // Mock the repository's save method to return the updated address
        when(addressRepo.save(address)).thenReturn(address);
 
     // Create a new address object with updated data
        Addresses updatedAddress = new Addresses();
        updatedAddress.setCity("New City");
        updatedAddress.setState("New State");
        updatedAddress.setStreet("456 New Street");
        updatedAddress.setZipCode("54321");
 
        
     // Call the service method to update the address
        addressService.updateAddress(1, updatedAddress);
 
     // Assert that the address was updated with the new data
        assertEquals("New City", address.getCity());
        assertEquals("New State", address.getState());
        assertEquals("456 New Street", address.getStreet());
        assertEquals("54321", address.getZipCode());
    }
 
    @Test
    void testUpdateAddressNotFound() {
    	// Mock the repository to return an empty Optional when the address ID is not found
        when(addressRepo.findById(1)).thenReturn(Optional.empty());
 
     // Create a new address object with some data
        Addresses updatedAddress = new Addresses();
        updatedAddress.setCity("New City");
 
        
     // Assert that an exception is thrown when trying to update a non-existent address
        assertThrows(NoSuchElementException.class, () -> {
            addressService.updateAddress(1, updatedAddress);
        });
    }
}