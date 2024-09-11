package com.example.demo.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.Addresses;
import com.example.demo.entity.Pets;
import com.example.demo.entity.Suppliers;
import com.example.demo.exceptionHandler.suppliers.SuppliersNotFoundException;
import com.example.demo.exceptionHandler.suppliers.SupplierInputInvalidException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByCityException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByNameException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByIdException;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PetsRepository;
import com.example.demo.repository.SuppliersRepository;

import jakarta.transaction.Transactional;


@Service
public class SuppliersServiceImpl implements SuppliersService{
	@Autowired
	SuppliersRepository suppliersRepository;
	
	@Autowired
	PetsRepository petsRepository;
	 
	// Retrieve all suppliers
	@Autowired
	AddressRepository addressRepository;
	public List<Suppliers> getAllSuppliers() throws SuppliersNotFoundException
	{
		List<Suppliers>suppliers=suppliersRepository.findAll();
		if(suppliers.isEmpty()) throw new SuppliersNotFoundException("No suppliers Found ");
		return suppliers;
	}
	
	 
	// Retrieve supplier by ID
	public Suppliers getSupplierById(int supplierId) throws SupplierNotFoundByIdException
	{
		if(suppliersRepository.findById(supplierId).isEmpty()) throw new SupplierNotFoundByIdException("Supplier is Not Found With Given Id"+supplierId);
		else {
			
		return suppliersRepository.findById(supplierId).get();
		}
		
	}
	
	
	// Retrieve supplier by name
	public Suppliers getSuppliersByName(String name)  throws SupplierNotFoundByNameException
	{
		if(suppliersRepository.findByName(name)==null) throw new SupplierNotFoundByNameException("Supplier Is Not Found By Name"+name);
		else {
			
			return suppliersRepository.findByName(name);
		}
		
	}
	
	
	// Retrieve suppliers by city
	public List<Suppliers> getSuppliersByCity(String city) throws SupplierNotFoundByCityException
	{
		if(suppliersRepository.findByCity(city).isEmpty()) throw new SupplierNotFoundByCityException("Supplier Is Not Found By City"+city);
		else {
			
			return suppliersRepository.findByCity(city);
		}
	}
	
	// Retrieve suppliers by state
	public List<Suppliers> getSuppliersByState(String state)  
	{
		return null;
	}
	
//	add a new supplier
	@Override
	@Transactional
	public ApiResponse addSupplier(SupplierDTO supplierDTO) throws SupplierInputInvalidException {
		// TODO Auto-generated method stub

		try {
			
		Addresses address = addressRepository.findById(supplierDTO.getAddressId()).get();
		 Suppliers supplier = new Suppliers();
	        supplier.setName(supplierDTO.getName());
	        supplier.setContactPerson(supplierDTO.getContactPerson());
	        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
	        supplier.setEmail(supplierDTO.getEmail());
	        supplier.setAddress(address);

	        suppliersRepository.save(supplier);
	        return new ApiResponse(LocalDate.now(),"supplier Added successfully"); 
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new SupplierInputInvalidException("Supplier Cannot Be Inserted"+supplierDTO.getName());
		}
		
	}
	
//	update a supplier
	@Override
	@Transactional
	public ApiResponse updateSupplier(int supplierId,SupplierDTO supplierDTO) throws SupplierNotFoundByIdException,MethodArgumentNotValidException {
		// TODO Auto-generated method stub
		Addresses address = addressRepository.findById(supplierDTO.getAddressId()).get();
		Suppliers supplier=suppliersRepository.findById(supplierId).get();
		if(supplierId<0 || supplier==null) throw new SupplierNotFoundByIdException("Id is not found to update"+supplierId);
		else {
			
		
			if(supplier!=null)
			{
				supplier.setName(supplierDTO.getName());
				supplier.setAddress(address);
				supplier.setContactPerson(supplierDTO.getContactPerson());
				supplier.setEmail(supplierDTO.getEmail());
				supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
			}
			suppliersRepository.save(supplier);
			 return new ApiResponse(LocalDate.now(),"supplier updated successfully"); 
		}
		
//		
		
	}
	
//	delete pet supplier
	@Override
	@Transactional
	public ApiResponse deleteSupplier(int supplierId) throws SupplierNotFoundByIdException{
		Suppliers supplier=suppliersRepository.findById(supplierId).get();
		if(supplierId<0 || supplier==null) throw new SupplierNotFoundByIdException("Id is not found to delete"+supplierId);
		
		else {
			suppliersRepository.deleteById(supplierId);
			 return new ApiResponse(LocalDate.now(),"supplier deleted successfully"); 
		}
		
	}


	


	
	
}
