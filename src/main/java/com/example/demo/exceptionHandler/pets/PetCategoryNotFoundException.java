package com.example.demo.exceptionHandler.pets;

public class PetCategoryNotFoundException extends Throwable{
	public PetCategoryNotFoundException(String message) {
		super(message);
	}
	public PetCategoryNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public PetCategoryNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
