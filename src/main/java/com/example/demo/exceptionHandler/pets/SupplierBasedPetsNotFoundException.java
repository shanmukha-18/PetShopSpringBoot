package com.example.demo.exceptionHandler.pets;

public class SupplierBasedPetsNotFoundException extends Throwable{
	public SupplierBasedPetsNotFoundException(String message) {
		super(message);
	}
	public SupplierBasedPetsNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public SupplierBasedPetsNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
