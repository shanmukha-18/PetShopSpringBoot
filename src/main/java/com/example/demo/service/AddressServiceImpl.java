package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Addresses;
import com.example.demo.exceptionHandler.address.AddressInputInvalidException;
import com.example.demo.exceptionHandler.address.AddressNotFoundByIdException;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
import com.example.demo.repository.AddressRepository;

import jakarta.transaction.Transactional;
 

 
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository addressRepository;
	
	// Retrieve all addresses from the repository; throw exception if none found
	@Override
	public List<Addresses> getAllAddresses() throws AddressNotFoundException  {
		
		List<Addresses>addresses=addressRepository.findAll();
		if(addresses.isEmpty()) throw new AddressNotFoundException("No Address Found");
		return addresses;
	}
	
	  
	// Retrieve address by ID; throw exception if address not found
	@Override
	public Addresses getAddressById(int address_id)  throws AddressNotFoundByIdException{
		
		if(addressRepository.findById(address_id).isEmpty()) throw new AddressNotFoundByIdException("Address Is Not Found With Given Id");
		else {
			return addressRepository.findById(address_id).get();
		}
	}
 
	
	 
	// Add new address; return response with success message or throw exception if input is invalid
	@Override
	@Transactional
	public ApiResponse addAddress(Addresses address) throws AddressInputInvalidException {
		try {
			addressRepository.save(address);
			return new ApiResponse(LocalDate.now(),"Address added successfully");
		}
		catch (Exception e) {
			throw new AddressInputInvalidException("Address Cannot Be Inserted");
		}
	}
 
    
//Update existing address by ID; throw exception if address not found
	@Override
	@Transactional
	public ApiResponse updateAddress(int address_id, Addresses updateAddress) throws AddressNotFoundByIdException {
		
		Addresses addresses=addressRepository.findById(address_id).get();
		if(address_id<0 || addresses==null) throw new AddressNotFoundByIdException("Id is not found to update");
		else {
			
			if(addresses!=null) {
				addresses.setCity(updateAddress.getCity());
				addresses.setState(updateAddress.getState());
				addresses.setStreet(updateAddress.getStreet());
				addresses.setZipCode(updateAddress.getZipCode());
				
			}
			addressRepository.save(addresses);
			return new ApiResponse(LocalDate.now(), "Address updated successfully");
		}
		
		
	}
}
