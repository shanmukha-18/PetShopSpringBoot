package com.example.demo.exceptionHandler.pets;

public class FoodBasedPetsNotFoundException extends Throwable{
	public FoodBasedPetsNotFoundException(String message) {
		super(message);
	}
	public FoodBasedPetsNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public FoodBasedPetsNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
