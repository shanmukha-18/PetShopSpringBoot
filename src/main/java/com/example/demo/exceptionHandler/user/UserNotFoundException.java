package com.example.demo.exceptionHandler.user;

public class UserNotFoundException extends Exception {
public UserNotFoundException(String message) {
	super(message);
}
}
