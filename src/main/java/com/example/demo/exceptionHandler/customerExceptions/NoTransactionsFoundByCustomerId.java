package com.example.demo.exceptionHandler.customerExceptions;


public class NoTransactionsFoundByCustomerId extends Exception{
	public NoTransactionsFoundByCustomerId(String str) {
		super(str);
	}
}
