package com.example.demo.controllers;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.VaccinationsDTO;
import com.example.demo.entity.Vaccinations;
import com.example.demo.exceptionHandler.vaccinations.*;
import com.example.demo.service.VaccinationServiceImpl;
 
import jakarta.validation.Valid;
import java.util.List;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server
@RequestMapping("/api/v1/vaccinations")  // Base path for vaccination-related endpoints
@Validated  // Enable validation annotations on the controller methods
public class VaccinationController {
 
    @Autowired
    VaccinationServiceImpl vaccinationService;  
 
 // Retrieve all vaccinations; throw exception if none found
    @GetMapping("/get")
    public ResponseEntity<List<Vaccinations>> getAllVaccinations() throws VaccinationNotFoundException {
        List<Vaccinations> vaccinations = vaccinationService.getAllVaccinations();
        if (vaccinations.isEmpty()) {
            throw new VaccinationNotFoundException("No vaccinations found.");
        }
        return new ResponseEntity<>(vaccinations, HttpStatus.OK);
    }
 
 // Validate ID and retrieve vaccination by ID; throw exceptions if invalid or not found
    @GetMapping("/{vaccinationId}")
    public ResponseEntity<Vaccinations> getVaccinationById(@PathVariable("vaccinationId") int vaccinationId) throws VaccinationNotFoundException {
        if (vaccinationId <= 0) {
            throw new InvalidVaccinationIdException("Invalid vaccination ID: " + vaccinationId);
        }
        Vaccinations vaccination = vaccinationService.getVaccinationById(vaccinationId);
        if (vaccination == null) {
            throw new VaccinationNotFoundException("Vaccination with ID " + vaccinationId + " not found.");
        }
        return new ResponseEntity<>(vaccination, HttpStatus.OK);
    }
 
 // Add a new vaccination; return HTTP status 201 Created
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addVaccination(@Valid @RequestBody VaccinationsDTO vaccination) throws InvalidVaccinationDataException {
 
         
        return new ResponseEntity<ApiResponse>(vaccinationService.addVaccination(vaccination), HttpStatus.CREATED);
    }
 
 // Update an existing vaccination ; return HTTP status 200 OK
    @PutMapping("/update/{vaccinationId}")
    public ResponseEntity<ApiResponse> updateVaccination( @PathVariable("vaccinationId") int vaccinationId,@Valid @RequestBody VaccinationsDTO updatedVaccination) {
       
        
       
        return new ResponseEntity<ApiResponse>(vaccinationService.updateVaccination(vaccinationId, updatedVaccination),HttpStatus.OK);
    }
 
 // Validate ID and delete vaccination by ID; return HTTP status 204 No Content
    @DeleteMapping("/delete/{vaccinationId}")
    public ResponseEntity<ApiResponse> deleteVaccination(@PathVariable("vaccinationId") int vaccinationId) throws InvalidVaccinationIdException, VaccinationNotFoundException {
        if (vaccinationId <= 0) {
            throw new InvalidVaccinationIdException("Invalid vaccination ID: " + vaccinationId);
        }
      
        return new ResponseEntity<ApiResponse>(  vaccinationService.deleteVaccination(vaccinationId),HttpStatus.NO_CONTENT);
    }
}