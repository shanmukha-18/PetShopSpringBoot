package com.example.demo.exceptionHandler.transactions;

public class TransactionNotFoundException extends Exception{
	public TransactionNotFoundException(String str) {
		super(str);
	}
}
