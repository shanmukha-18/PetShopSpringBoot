package com.example.demo.exceptionHandler.pets;

public class PetAlreadyExistsException extends Throwable{
	public PetAlreadyExistsException(String message) {
		super(message);
	}
	public PetAlreadyExistsException(String message,Throwable e) {
		super(message,e);
	}
	public PetAlreadyExistsException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
