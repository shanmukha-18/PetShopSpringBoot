package com.example.demo.service;
 
 
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PetCategories;
import com.example.demo.entity.PetFood;
import com.example.demo.entity.Pets;
import com.example.demo.entity.Suppliers;
import com.example.demo.entity.Transactions;
import com.example.demo.entity.Vaccinations;
import com.example.demo.exceptionHandler.pets.FoodBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.InvalidPetInputException;
import com.example.demo.exceptionHandler.pets.PetAlreadyExistsException;
import com.example.demo.exceptionHandler.pets.PetCategoryNotFoundException;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.pets.PetsNotFoundException;
import com.example.demo.exceptionHandler.pets.SupplierBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.pets.VaccinationBasedPetsNotFoundException;
import com.example.demo.repository.PetCategoriesRepository;
import com.example.demo.repository.PetFoodRepository;
import com.example.demo.repository.PetsRepository;
import com.example.demo.repository.SuppliersRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.VaccinationRepository;

import jakarta.transaction.Transactional;

import com.example.demo.*;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PetDTO;
//import com.example.demo.dto.PetInit;
 
 
@Service
public class PetServiceImpl implements PetService{
	@Autowired
	private PetsRepository petRepository;
 
	@Autowired
	
	private PetCategoriesRepository petCategoriesRepository;
	@Autowired
	private TransactionRepository transactionRepository;
		
	@Autowired
	private VaccinationRepository vaccinationRepository;
	
	@Autowired
	private PetFoodRepository petFoodRepository;
	
	@Autowired
	private SuppliersRepository suppliersRepository;

	public PetServiceImpl() {
	}
	
	// Retrieve all pets with their details
	@Override
	public List<Pets> getAllPets() throws PetsNotFoundException{
		List<Pets> p = petRepository.findAllWithDetails();
		if(p.isEmpty()) {
			throw new PetsNotFoundException("Validation failed: No pets are available in the pet shop"); 
		}
		return p;
	}
	
    
//Retrieve pet by ID
	@Override
	public Pets getPetsById(int id) throws PetIdNotFoundException{
		try {
			Pets p =petRepository.findById(id).get();
			return p;
		}
		catch(NoSuchElementException e) {
			throw new PetIdNotFoundException("Validation failed: Pet id not found");
		}
	}
	
    
// Retrieve pets by category name
	@Override
	public List<Pets> getPetsByCategory(String category) throws PetCategoryNotFoundException {
		List<Pets> l = petRepository.findByCategoryName(category);
		if(l.isEmpty()) {
			throw new PetCategoryNotFoundException("Validation failed: No pets in the given category");
		}
		return petRepository.findByCategoryName(category);
	}

	
    
//Find food pet by ID
    @Override
    public List<Object[]> getPetFoodByPetId(int petId) throws FoodBasedPetsNotFoundException, PetIdNotFoundException {
        // Find the pet by ID
        Optional<Pets> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new PetIdNotFoundException("Pet with ID " + petId + " not found");
        }

        Pets pet = optionalPet.get();

        // Get the list of PetFood associated with this pet
        List<PetFood> petFoods = pet.getPetFoods();
        if (petFoods == null || petFoods.isEmpty()) {
            throw new FoodBasedPetsNotFoundException("No pet food found for pet with ID " + petId);
        }

        // Convert List<PetFood> to List<Object[]>
        List<Object[]> result = new ArrayList<>();
        for (PetFood petFood : petFoods) {
            Object[] petFoodArray = new Object[] {
                petFood.getFoodId(),
                petFood.getName(),
                petFood.getBrand(),
                petFood.getType(),
                petFood.getQuantity(),
                petFood.getPrice()
            };
            result.add(petFoodArray);
        }

        return result;
    }

 
 // Retrieve transactions by pet ID
    @Override
    public Transactions getTransactionByPetId(int petId) throws PetIdNotFoundException {
        List<Transactions> transactions = transactionRepository.findTransactionsOfPetByPetId(petId);
        
        if (transactions.isEmpty()) {
            throw new PetIdNotFoundException("No transactions found for pet with ID " + petId);
        }

        // Assuming you want the first transaction; adjust as needed
        return transactions.get(0);
    }


    
 // Get list of suppliers associated with this pet
    @Override
    public List<Object[]> getSupplierByPetId(int petId) throws SupplierBasedPetsNotFoundException, PetIdNotFoundException {
        // Find the pet by ID
        Optional<Pets> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new PetIdNotFoundException("Pet with ID " + petId + " not found");
        }

        Pets pet = optionalPet.get();

        // Get the list of Suppliers associated with this pet
        List<Suppliers> suppliers = pet.getPetSuppliers();
        if (suppliers == null || suppliers.isEmpty()) {
            throw new SupplierBasedPetsNotFoundException("No suppliers found for pet with ID " + petId);
        }

        // Convert List<Suppliers> to List<Object[]>
        List<Object[]> result = new ArrayList<>();
        for (Suppliers supplier : suppliers) {
            Object[] supplierArray = new Object[] {
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContactPerson(),
                supplier.getPhoneNumber(),
                supplier.getEmail(),
                supplier.getAddress() != null ? supplier.getAddress().getAddressId() : null // Assuming you want address ID
            };
            result.add(supplierArray);
        }

        return result;
    }
    
    // Retrieve pets by breed
    
    public List<Pets> getPetsByBreed(String breed) {
        return petRepository.findByBreed(breed);
    }
    
    
// Find all vaccinations associated with this pet
    public List<Pets> getPetsByVaccination(int petId) throws VaccinationBasedPetsNotFoundException, PetIdNotFoundException {
        // Find the pet by ID
        Optional<Pets> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new PetIdNotFoundException("Pet with ID " + petId + " not found");
        }

        Pets pet = optionalPet.get();

        // Find all vaccinations associated with this pet
        List<Vaccinations> vaccinations = pet.getPetVaccinations();
        if (vaccinations == null || vaccinations.isEmpty()) {
            throw new VaccinationBasedPetsNotFoundException("No vaccinations found for pet with ID " + petId);
        }

        // Collect the list of pets associated with these vaccinations
        List<Pets> result = new ArrayList<>();
        for (Vaccinations vaccination : vaccinations) {
            List<Pets> pets = vaccination.getPets();
            if (pets != null && !pets.isEmpty()) {
                result.addAll(pets);
            }
        }

        if (result.isEmpty()) {
            throw new VaccinationBasedPetsNotFoundException("No pets found for the vaccinations associated with pet ID " + petId);
        }
        return result;
    }

 
    
 // Create a new Pets entity
    public ApiResponse addPet(PetDTO petDTO) {
        try {
            // Create a new Pets entity
            Pets pet = new Pets();
            pet.setName(petDTO.getName());
            pet.setBreed(petDTO.getBreed());
            pet.setAge(petDTO.getAge()); // Allow null if optional
            pet.setPrice(petDTO.getPrice());
            pet.setDescription(petDTO.getDescription()); // Allow null if optional
            pet.setImageUrl(petDTO.getImageUrl()); // Allow null if optional

            // Fetch and set category
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(petDTO.getCategoryName());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
           
            PetCategories category = petCategoriesRepository.findByName(petDTO.getCategoryName());
           System.out.println(category);
            if (category == null) {
                return new ApiResponse(LocalDate.now(), "Category not found");
            }
            pet.setCategory(category);

            // Fetch and set pet foods
            List<PetFood> petFoods = petFoodRepository.findByNameIn(petDTO.getPetFoodNames());
            if (petFoods.size() != petDTO.getPetFoodNames().size()) {
                return new ApiResponse(LocalDate.now(), "Some pet food items not found");
            }
            pet.setPetFoods(petFoods);

            // Fetch and set vaccinations
            List<Vaccinations> petVaccinations = vaccinationRepository.findByNameIn(petDTO.getVaccinationNames());
            pet.setPetVaccinations(petVaccinations);

            // Fetch and set suppliers
            List<Suppliers> petSuppliers = suppliersRepository.findByNameIn(petDTO.getSupplierNames());
            if (petSuppliers.size() != petDTO.getSupplierNames().size()) {
                return new ApiResponse(LocalDate.now(), "Some pet suppliers are not found");
            }
            pet.setPetSuppliers(petSuppliers);

            // Save the pet
            Pets savedPet = petRepository.save(pet);
            
            // Return success response
            return new ApiResponse(LocalDate.now(), "Pet added successfully");
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error while adding pet: " + e.getMessage());
            // Return error response
            return new ApiResponse(LocalDate.now(), "Failed to add pet: " + e.getMessage());
        }
    }

@Override
    @Transactional
    public ApiResponse updatePet(int petId, PetDTO petDTO) throws PetIdNotFoundException, InvalidPetInputException {

	  try {
          // Find the pet by ID
          Pets pet = petRepository.findById(petId)
              .orElseThrow(() -> new RuntimeException("Pet not found"));

          // Update pet details
          pet.setName(petDTO.getName());
          pet.setBreed(petDTO.getBreed());
          pet.setAge(petDTO.getAge()); // Allow null if optional
          pet.setPrice(petDTO.getPrice());
          pet.setDescription(petDTO.getDescription()); // Allow null if optional
          pet.setImageUrl(petDTO.getImageUrl()); // Allow null if optional

          // Update category
          PetCategories category = petCategoriesRepository.findByName(petDTO.getCategoryName());
          if (category == null) {
              return new ApiResponse(LocalDate.now(), "Category not found");
          }
          pet.setCategory(category);

          // Update pet food items
          List<PetFood> petFoods = petFoodRepository.findByNameIn(petDTO.getPetFoodNames());
          pet.setPetFoods(petFoods);

          // Update vaccinations
          List<Vaccinations> petVaccinations = vaccinationRepository.findByNameIn(petDTO.getVaccinationNames());
          pet.setPetVaccinations(petVaccinations);

          // Update suppliers
          List<Suppliers> petSuppliers = suppliersRepository.findByNameIn(petDTO.getSupplierNames());
          pet.setPetSuppliers(petSuppliers);

          // Save the updated pet
          Pets updatedPet = petRepository.save(pet);

          // Return success response
          return new ApiResponse(LocalDate.now(), "Pet updated successfully");
      } catch (Exception e) {
          // Log the exception
          System.err.println("Error while updating pet: " + e.getMessage());
          // Return error response
          return new ApiResponse(LocalDate.now(), "Failed to update pet: " + e.getMessage());
      }
  
    }
 
    @Override
    @Transactional
    public ApiResponse deletePet(int petId) throws PetIdNotFoundException {
        if (!petRepository.existsById(petId)) {
            throw new PetIdNotFoundException("Pet with ID " + petId + " not found");
        }
        
//        petFoodRepository.deleteById(petId);
//        vaccinationRepository.deleteByPetId(petId);
//        suppliersRepository.deleteByPetId(petId);
        transactionRepository.deleteByPetId(petId);
        petRepository.deleteById(petId);
        return new ApiResponse(LocalDate.now(),"Pet Deleted successfully");
    }

}

