package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Addresses;
import com.example.demo.exceptionHandler.address.AddressInputInvalidException;
import com.example.demo.exceptionHandler.address.AddressNotFoundByIdException;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
import com.example.demo.service.AddressServiceImpl;
 
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
 
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/address")
 
public class AddressController {
	@Autowired
	AddressServiceImpl addressService;
	
//	Retrieves all addresses from the database.
	@GetMapping("/get")
	public ResponseEntity<List<Addresses>>getAllAddresses() throws AddressNotFoundException{
		return new ResponseEntity<List<Addresses>>(addressService.getAllAddresses(),HttpStatus.OK);
	}
	
//	Retrieves a specific address by its ID.
	@GetMapping("/{address_id}")
	public ResponseEntity<Addresses>getAddressById(@PathVariable("address_id") int address_Id) throws AddressNotFoundByIdException{
		 return new ResponseEntity<Addresses>(addressService.getAddressById(address_Id),HttpStatus.OK);
	}
	
//	Adds a new address to the database.
	@PostMapping("/add")
	public ResponseEntity<ApiResponse>addAddress(@Valid @RequestBody Addresses addresses) throws AddressInputInvalidException{
		addressService.addAddress(addresses);
		
		 return new ResponseEntity<ApiResponse>(addressService.addAddress(addresses),HttpStatus.CREATED);
	}
	
//	Updates an existing address by its ID.
	@PutMapping("/update/{address_Id}")
	public ResponseEntity<ApiResponse>updateAddress(@PathVariable("address_Id") int address_Id, @Valid @RequestBody Addresses updateAddress) throws AddressNotFoundByIdException {
//		addressService.updateAddress(address_Id,updateAddress);
		 return new ResponseEntity<ApiResponse>(addressService.updateAddress(address_Id,updateAddress),HttpStatus.OK);
			
	}
	
	
}