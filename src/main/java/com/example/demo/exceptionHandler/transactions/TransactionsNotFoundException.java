package com.example.demo.exceptionHandler.transactions;

public class TransactionsNotFoundException extends Exception{
	public TransactionsNotFoundException(String str) {
		super(str);
	}
}
