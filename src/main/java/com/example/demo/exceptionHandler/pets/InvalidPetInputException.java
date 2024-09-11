package com.example.demo.exceptionHandler.pets;

public class InvalidPetInputException extends Throwable{
	public InvalidPetInputException(String message) {
		super(message);
	}
	public InvalidPetInputException(String message,Throwable e) {
		super(message,e);
	}
	public InvalidPetInputException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
