package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.Suppliers;
import com.example.demo.exceptionHandler.suppliers.SuppliersNotFoundException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByNameException;
import com.example.demo.exceptionHandler.suppliers.SupplierInputInvalidException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByCityException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByIdException;




public interface SuppliersService {
	List<Suppliers> getAllSuppliers() throws SuppliersNotFoundException;
	Suppliers getSupplierById(int supplierId) throws SupplierNotFoundByIdException ;
	Suppliers getSuppliersByName(String name) throws SupplierNotFoundByNameException;
	List<Suppliers> getSuppliersByCity(String city) throws SupplierNotFoundByCityException;
//	List<Suppliers> getSuppliersByState(String state) ;
	ApiResponse addSupplier(SupplierDTO supplierDTO) throws SupplierInputInvalidException;
	ApiResponse updateSupplier(int supplierId,SupplierDTO supplierDTO)throws SupplierNotFoundByIdException,MethodArgumentNotValidException;
	
	ApiResponse deleteSupplier(int supplierId) throws SupplierNotFoundByIdException;
	

}
