package com.example.demo.service;
 
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.VaccinationsDTO;
import com.example.demo.entity.Vaccinations;
import com.example.demo.exceptionHandler.vaccinations.*;

import java.util.List;
 
 
public interface VaccinationService {
    
	 List<Vaccinations> getAllVaccinations() throws VaccinationNotFoundException;
	
	 Vaccinations getVaccinationById(int vaccinationId) throws InvalidVaccinationIdException;
 
	 ApiResponse addVaccination(VaccinationsDTO vaccinationsDTO) throws InvalidVaccinationDataException;
 
	 ApiResponse updateVaccination(int vaccinationId, VaccinationsDTO vaccination) ;
 
	 ApiResponse deleteVaccination(int vaccinationId) throws VaccinationNotFoundException;
}