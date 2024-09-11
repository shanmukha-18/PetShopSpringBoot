package com.example.demo.dto;
 
import java.time.LocalDate;
 
public class ApiResponse {
	private LocalDate date;
	private String message;
	public ApiResponse() {}
	public ApiResponse(LocalDate date, String message) {
		super();
		this.date = date;
		this.message = message;
	}
 
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}