package com.example.demo.exceptionHandler.pets;

public class TransactionBasedPetsNotFoundException extends Throwable{
	public TransactionBasedPetsNotFoundException(String message) {
		super(message);
	}
	public TransactionBasedPetsNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public TransactionBasedPetsNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
