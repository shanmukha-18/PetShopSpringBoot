package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Addresses;
import com.example.demo.entity.Suppliers;
import com.example.demo.exceptionHandler.address.AddressInputInvalidException;
import com.example.demo.exceptionHandler.address.AddressNotFoundByIdException;
import com.example.demo.exceptionHandler.address.AddressNotFoundException;
 

 
public interface AddressService {
	List<Addresses> getAllAddresses() throws AddressNotFoundException;
	Addresses getAddressById(int address_id) throws  AddressNotFoundByIdException ;
	ApiResponse addAddress(Addresses address) throws AddressInputInvalidException ;
	ApiResponse updateAddress(int address_id,Addresses updateAddress) throws AddressNotFoundByIdException  ;
//	SuccessResponse addAddress(Addresses address) throws MismatchDataTypeException;
//	SuccessResponse updateAddress(int address_id,Addresses updatedAddress)throws MismatchDataTypeException,AddressIdNotFoundException;
 
 
	
}
 
