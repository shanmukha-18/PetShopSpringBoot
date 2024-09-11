package com.example.demo.exceptionHandler.pets;

public class VaccinationBasedPetsNotFoundException extends Throwable{
	public VaccinationBasedPetsNotFoundException(String message) {
		super(message);
	}
	public VaccinationBasedPetsNotFoundException(String message,Throwable e) {
		super(message,e);
	}
	public VaccinationBasedPetsNotFoundException(String message,Throwable e,boolean enablesupression,boolean enablestacktrace) {
		super( message,e,enablesupression,enablestacktrace);
	}
}
