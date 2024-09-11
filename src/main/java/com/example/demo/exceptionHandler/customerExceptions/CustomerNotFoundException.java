package com.example.demo.exceptionHandler.customerExceptions;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException(String str) {
		super(str);
	}
}
