package com.example.demo.petShopTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Addresses;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Transactions;
import com.example.demo.enums.transaction_status;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomerNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.CustomersNotFoundException;
import com.example.demo.exceptionHandler.customerExceptions.NoTransactionsFoundByCustomerId;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PetsRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

   @Mock
   private CustomerRepository customerRepo;

   @Mock
   private TransactionRepository transRepo;

   @Mock
   private PetsRepository petsRepo;

   @Mock
   private AddressRepository addressRepo;

   @InjectMocks
   private CustomerServiceImpl customerService;

   @BeforeEach
   public void setup() {
       MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testCreateCustomer() {
       // Setup
       CustomerDTO customerDTO = new CustomerDTO();
       customerDTO.setFirstName("John");
       customerDTO.setLastName("Doe");
       customerDTO.setEmail("john.doe@example.com");
       customerDTO.setPhoneNumber("1234567890");
       customerDTO.setAddressId(1);

       Addresses address = new Addresses();
       when(addressRepo.findById(1)).thenReturn(Optional.of(address));

       Customers customer = new Customers();
       when(customerRepo.save(any(Customers.class))).thenReturn(customer);

       // Execute
       ApiResponse response = customerService.createCustomer(customerDTO);

       // Verify
       assertEquals(LocalDate.now(), response.getDate());
       assertEquals("Customer created successfully", response.getMessage());
       verify(customerRepo, times(1)).save(any(Customers.class));
   }

   @Test
   public void testUpdateCustomer() throws CustomerNotFoundException {
       // Setup
       CustomerDTO customerDTO = new CustomerDTO();
       customerDTO.setFirstName("Jane");
       customerDTO.setLastName("Doe");
       customerDTO.setEmail("jane.doe@example.com");
       customerDTO.setPhoneNumber("0987654321");
       customerDTO.setAddressId(2);

       Customers existingCustomer = new Customers();
       when(customerRepo.getCustomerById(1)).thenReturn(existingCustomer);

       Addresses address = new Addresses();
       when(addressRepo.findById(2)).thenReturn(Optional.of(address));

       // Execute
       ApiResponse response = customerService.updateCustomer(1, customerDTO);

       // Verify
       assertEquals(LocalDate.now(), response.getDate());
       assertEquals("Customer updated successfully", response.getMessage());
       verify(customerRepo, times(1)).save(existingCustomer);
   }

   @Test
   public void testDeleteCustomerById() throws CustomerNotFoundException {
       // Setup
       Customers customer = new Customers();
       when(customerRepo.getCustomerById(1)).thenReturn(customer);

       // Execute
       ApiResponse response = customerService.deleteCustomerById(1);

       // Verify
       assertEquals(LocalDate.now(), response.getDate());
       assertEquals("Customer deleted successfully", response.getMessage());
       verify(customerRepo, times(1)).deleteById(1);
   }

   @Test
   public void testRetrieveAllCustomers() throws CustomersNotFoundException {
       // Setup
       List<Customers> customers = new ArrayList<>();
       customers.add(new Customers());
       when(customerRepo.findAll()).thenReturn(customers);

       // Execute
       List<Customers> result = customerService.getAllCustomers();

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testRetrieveAllCustomersNotFound() {
       // Setup
       when(customerRepo.findAll()).thenReturn(new ArrayList<>());

       // Execute & Verify
       assertThrows(CustomersNotFoundException.class, () -> {
           customerService.getAllCustomers();
       });
   }

   @Test
   public void testGetCustomerById() throws CustomerNotFoundException {
       // Setup
       Customers customer = new Customers();
       when(customerRepo.getCustomerById(1)).thenReturn(customer);

       // Execute
       Customers result = customerService.getCustomerById(1);

       // Verify
       assertNotNull(result);
   }

   @Test
   public void testGetCustomerByIdNotFound() {
       // Setup
       when(customerRepo.getCustomerById(1)).thenReturn(null);

       // Execute & Verify
       assertThrows(CustomerNotFoundException.class, () -> {
           customerService.getCustomerById(1);
       });
   }

   @Test
   public void testGetCustomerByCity() throws CustomersNotFoundException {
       // Setup
       List<Customers> customers = new ArrayList<>();
       customers.add(new Customers());
       when(customerRepo.findByCity("New York")).thenReturn(customers);

       // Execute
       List<Customers> result = customerService.getCustomerBycity("New York");

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testGetCustomerByCityNotFound() {
       // Setup
       when(customerRepo.findByCity("New York")).thenReturn(new ArrayList<>());

       // Execute & Verify
       assertThrows(CustomersNotFoundException.class, () -> {
           customerService.getCustomerBycity("New York");
       });
   }

   @Test
   public void testFindByState() throws CustomersNotFoundException {
       // Setup
       List<Customers> customers = new ArrayList<>();
       customers.add(new Customers());
       when(customerRepo.findByState("California")).thenReturn(customers);

       // Execute
       List<Customers> result = customerService.findByState("California");

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testFindByStateNotFound() {
       // Setup
       when(customerRepo.findByState("California")).thenReturn(new ArrayList<>());

       // Execute & Verify
       assertThrows(CustomersNotFoundException.class, () -> {
           customerService.findByState("California");
       });
   }

   @Test
   public void testFindByCustomerId() throws NoTransactionsFoundByCustomerId {
       // Setup
       List<Transactions> transactions = new ArrayList<>();
       transactions.add(new Transactions());
       when(customerRepo.findByCustomerId(1)).thenReturn(transactions);

       // Execute
       List<Transactions> result = customerService.findByCustomerId(1);

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testFindByCustomerIdNotFound() {
       // Setup
       when(customerRepo.findByCustomerId(1)).thenReturn(new ArrayList<>());

       // Execute & Verify
       assertThrows(NoTransactionsFoundByCustomerId.class, () -> {
           customerService.findByCustomerId(1);
       });
   }

   @Test
   public void testFindCustomersByTransactionStatus() {
       // Setup
       List<Customers> customers = new ArrayList<>();
       customers.add(new Customers());
       when(customerRepo.findCustomersByTransactionStatus(transaction_status.Success)).thenReturn(customers);

       // Execute
       List<Customers> result = customerService.findCustomersByTransactionStatus(transaction_status.Success);

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testFindCustomerByNoTransaction() {
       // Setup
       List<Customers> customers = new ArrayList<>();
       customers.add(new Customers());
       when(customerRepo.findCustomerByNotransaction()).thenReturn(customers);

       // Execute
       List<Customers> result = customerService.findCustomerByNotransaction();

       // Verify
       assertEquals(1, result.size());
   }

   @Test
   public void testFindCustomerByFirstNameAndLastName() throws CustomersNotFoundException {
       // Setup
       Customers customer = new Customers();
       when(customerRepo.findCustomerByfirstNameAndlastName("John", "Doe")).thenReturn(customer);

       // Execute
       Customers result = customerService.findCustomerByfirstNameAndlastName("John", "Doe");

       // Verify
       assertNotNull(result);
   }

   @Test
   public void testFindCustomerByFirstNameAndLastNameNotFound() {
       // Setup
       when(customerRepo.findCustomerByfirstNameAndlastName("John", "Doe")).thenReturn(null);

       // Execute & Verify
       assertThrows(CustomersNotFoundException.class, () -> {
           customerService.findCustomerByfirstNameAndlastName("John", "Doe");
       });
   }
}