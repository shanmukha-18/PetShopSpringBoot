package com.example.demo.exceptionHandler.address;

public class AddressNotFoundByIdException  extends Exception{
	 public AddressNotFoundByIdException(String message) {
		 super(message);
	 }
}