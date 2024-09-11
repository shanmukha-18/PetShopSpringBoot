package com.example.demo.exceptionHandler.suppliers;

public class SupplierNotFoundByIdException  extends Exception{
 public SupplierNotFoundByIdException(String message) {
	 super(message);
 }
}
