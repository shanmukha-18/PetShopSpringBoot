package com.example.demo.exceptionHandler.vaccinations;

public class VaccinationNotFoundException extends Exception {

    public VaccinationNotFoundException(String message) {
        super(message);
    }
}