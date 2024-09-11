package com.example.demo.dto;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String Details;
	
	public ErrorDetails() {
		
	}
 
	public ErrorDetails(Date date, String message, String details) {
		super();
		this.timestamp = date;
		this.message = message;
		this.Details = details;
	}
 
	public Date getTimestamp() {
		return timestamp;
	}
 
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
 
	public String getDetails() {
		return Details;
	}
 
	public void setDetails(String details) {
		Details = details;
	}
 
	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", message=" + message + ", Details=" + Details + "]";
	}
}
