package com.example.demo.exceptionHandler.pets;

public class PetsNotFoundException extends Throwable{
	public PetsNotFoundException(String message) {
		super(message);
	}
	public PetsNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public PetsNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
