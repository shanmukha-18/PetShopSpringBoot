package com.example.demo.exceptionHandler.vaccinations;

public class InvalidVaccinationDataException extends Exception {
    public InvalidVaccinationDataException(String message) {
        super(message);
    }
}