package com.example.demo.exceptionHandler.suppliers;

public class SupplierNotFoundByNameException  extends Exception{
 public SupplierNotFoundByNameException(String message) {
	 super(message);
 }
}
