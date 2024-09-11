package com.example.demo.service;
 
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.VaccinationsDTO;
import com.example.demo.entity.Vaccinations;
import com.example.demo.exceptionHandler.vaccinations.*;
import com.example.demo.repository.VaccinationRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.NonReadableChannelException;
import java.time.LocalDate;
import java.util.List;
 
@Service
public class VaccinationServiceImpl implements VaccinationService {
 
	    @Autowired
	    private final VaccinationRepository vaccinationRepository;
 
	    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository) {
	        this.vaccinationRepository = vaccinationRepository;
	    }
 
	    
	    @Override
	    public List<Vaccinations> getAllVaccinations() throws VaccinationNotFoundException {
	        List<Vaccinations> vaccinations = vaccinationRepository.findAll();
	        if (vaccinations.isEmpty()) {
	            throw new VaccinationNotFoundException("No vaccinations found.");
	        }
	        return vaccinations;
	    }
 
//Fetch vaccination by ID and handle case if not found.
	    @Override
	    public Vaccinations getVaccinationById(int vaccinationId) throws InvalidVaccinationIdException {
	        if (vaccinationId <= 0) {
	            throw new InvalidVaccinationIdException("Invalid vaccination ID: " + vaccinationId);
	        }
	        return vaccinationRepository.findById(vaccinationId)
	                .orElseThrow(() -> new InvalidVaccinationIdException("Vaccination with ID " + vaccinationId + " not found."));
	    }
 
	    
//	    create a vaccination
	    @Override
	    @Transactional
	    public ApiResponse addVaccination(VaccinationsDTO vaccinationDTO) throws InvalidVaccinationDataException {
	    	try {
	    		   Vaccinations vaccination = new Vaccinations();
	    	        vaccination.setName(vaccinationDTO.getName());
	    	        vaccination.setDescription(vaccinationDTO.getDescription());
	    	        vaccination.setPrice(vaccinationDTO.getPrice());
	    	        vaccination.setAvailable(vaccinationDTO.getAvailable());

	    	        vaccinationRepository.save(vaccination);
	    	        return new ApiResponse(LocalDate.now(),"vaccination added successfully");
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new InvalidVaccinationDataException("Vaccination Cannot Be Inserted");
			}
	    }
// update a vaccinaiton
	    @Override
	    @Transactional
	    public ApiResponse updateVaccination(int vaccinationId, VaccinationsDTO vaccinationsDTO)  {
	    	 Vaccinations existingVaccination = vaccinationRepository.findById(vaccinationId).get();
	    	 if(existingVaccination!=null) {
	    		
	    	           
 
	    	    // Update the existing vaccination with the new data
	    	    existingVaccination.setName(vaccinationsDTO.getName());
	    	    existingVaccination.setDescription(vaccinationsDTO.getDescription());
	    	    existingVaccination.setPrice(vaccinationsDTO.getPrice());
	    	 }
	    	 vaccinationRepository.save(existingVaccination);
	    	  return new ApiResponse(LocalDate.now(),"vaccination updated successfully");

	    }
// delete vaccination
	    @Override
	    @Transactional
	    public ApiResponse deleteVaccination(int vaccinationId) throws InvalidVaccinationIdException, VaccinationNotFoundException {
	        if (vaccinationId <= 0) {
	            throw new InvalidVaccinationIdException("Invalid vaccination ID: " + vaccinationId);
	        }
	        if (!vaccinationRepository.existsById(vaccinationId)) {
	            throw new VaccinationNotFoundException("Vaccination with ID " + vaccinationId + " not found.");
	        }
	        vaccinationRepository.deleteById(vaccinationId);
	        return new ApiResponse(LocalDate.now(),"vaccination deleted successfully");
	    }
}
 
 