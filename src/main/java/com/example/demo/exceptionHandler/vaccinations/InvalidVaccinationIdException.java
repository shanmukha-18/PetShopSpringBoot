package com.example.demo.exceptionHandler.vaccinations;


public class InvalidVaccinationIdException extends RuntimeException {
    public InvalidVaccinationIdException(String message) {
        super(message);
    }
}