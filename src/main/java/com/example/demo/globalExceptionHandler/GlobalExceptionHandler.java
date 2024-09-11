package com.example.demo.globalExceptionHandler;

import java.util.Date;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.exceptionHandler.suppliers.SuppliersNotFoundException;
import com.example.demo.exceptionHandler.user.UserInputInvalidException;
import com.example.demo.exceptionHandler.user.UserNotFoundException;
import com.example.demo.exceptionHandler.vaccinations.*;
import com.example.demo.exceptionHandler.pets.PetIdNotFoundException;
import com.example.demo.exceptionHandler.pets.SupplierBasedPetsNotFoundException;
import com.example.demo.exceptionHandler.suppliers.SupplierInputInvalidException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByCityException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByIdException;
import com.example.demo.exceptionHandler.suppliers.SupplierNotFoundByNameException;
import com.example.demo.exceptionHandler.address.*;
import com.example.demo.exceptionHandler.employees.EmployeeInputInvalidException;
import com.example.demo.exceptionHandler.employees.EmployeeNotFoundException;
import com.example.demo.exceptionHandler.employees.EmployeesNotFoundException;
import com.example.demo.exceptionHandler.petCategories.*;
import com.example.demo.exceptionHandler.petFood.*;
import com.example.demo.exceptionHandler.petFood.PetFoodIdNotFoundException;
import com.example.demo.exceptionHandler.pets.*;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });

	        ErrorDetails errorDetails = new ErrorDetails(
	                new Date(), 
	                "Validation Failed", 
	                errors.toString()
	        );

	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
	
@ExceptionHandler(SuppliersNotFoundException.class)
public ResponseEntity<ErrorDetails>SuppliersNotFoundException(SuppliersNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(SupplierNotFoundByIdException.class)
public ResponseEntity<ErrorDetails>SupplierNotByIdException(SupplierNotFoundByIdException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(SupplierNotFoundByCityException.class)
public ResponseEntity<ErrorDetails>SupplierNotFoundByCity(SupplierNotFoundByCityException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(SupplierNotFoundByNameException.class)
public ResponseEntity<ErrorDetails>SupplierNotFoundByName(SupplierNotFoundByNameException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(SupplierInputInvalidException.class)
public ResponseEntity<ErrorDetails>SupplierInputException(SupplierInputInvalidException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
}
//@ExceptionHandler(SupplierInputInvalidException.class)
//public ResponseEntity<ErrorDetails>updateSupplierException(SupplierInputInvalidException ex,WebRequest req){
//	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
//}




//Pet Categories

@ExceptionHandler(PetCategoriesNotFoundException.class)
public ResponseEntity<ErrorDetails>PetCategoriesNotFoundException(PetCategoriesNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(PetCategoriesNotFoundByIdException.class)
public ResponseEntity<ErrorDetails>PetCategoriesNotByIdException(PetCategoriesNotFoundByIdException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(PetCategoriesNotFoundByNameException.class)
public ResponseEntity<ErrorDetails>PetCategoriesNotFoundByName(PetCategoriesNotFoundByNameException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(PetCategoriesInputInvalidException.class)
public ResponseEntity<ErrorDetails>PetCategoriesInputException(PetCategoriesInputInvalidException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
}

//Pets
@ExceptionHandler(PetIdNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingPetIdException(PetIdNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}
@ExceptionHandler(PetCategoryNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingPetCategoryNotFoundException(PetCategoryNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(PetsNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingPetsNotFoundException(PetsNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(FoodBasedPetsNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingFoodBasedPetsNotFoundException(FoodBasedPetsNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(InvalidPetInputException.class)
public ResponseEntity<ErrorDetails>handlingInvalidPetInputException(InvalidPetInputException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(PetAlreadyExistsException.class)
public ResponseEntity<ErrorDetails>handlingPetAlreadyExistsException(PetAlreadyExistsException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(SupplierBasedPetsNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingSupplierBasedPetsNotFoundException(SupplierBasedPetsNotFoundException ex,WebRequest req){
	return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
}
@ExceptionHandler(VaccinationBasedPetsNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingVaccinationBasedPetsNotFoundException(VaccinationBasedPetsNotFoundException ex,WebRequest req){
return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
}
@ExceptionHandler(TransactionBasedPetsNotFoundException.class)
public ResponseEntity<ErrorDetails>handlingTransactionBasedPetsNotFoundException(TransactionBasedPetsNotFoundException ex,WebRequest req){
return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(),ex.getMessage(),req.getDescription(false)), HttpStatus.CONFLICT);
}


//Vaccinations

@ExceptionHandler(VaccinationNotFoundException.class)
public ResponseEntity<ErrorDetails> handleVaccinationNotFoundException(VaccinationNotFoundException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(InvalidVaccinationDataException.class)
public ResponseEntity<ErrorDetails> handleInvalidVaccinationDataException(InvalidVaccinationDataException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(InvalidVaccinationIdException.class)
public ResponseEntity<ErrorDetails> handleInvalidVaccinationIdException(InvalidVaccinationIdException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred", req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

}

//Adddress
@ExceptionHandler(AddressNotFoundException.class)
public ResponseEntity<ErrorDetails> handleAddressNotFoundException(AddressNotFoundException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}
 
@ExceptionHandler(AddressNotFoundByIdException.class)
public ResponseEntity<ErrorDetails> handleAddressNotFoundByIdException(AddressNotFoundByIdException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}
 
@ExceptionHandler(AddressIdNotFoundException.class)
public ResponseEntity<ErrorDetails> handleAddressIdNotFoundException(AddressIdNotFoundException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}
 
@ExceptionHandler(AddressInputInvalidException.class)
public ResponseEntity<ErrorDetails> handleAddressInputInvalidException(AddressInputInvalidException ex, WebRequest req) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
}

//PetFood
@ExceptionHandler(NoPetFoodFoundException.class)
public ResponseEntity<ErrorDetails> handleNoPetFoodFoundException(NoPetFoodFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(PetFoodIdNotFoundException.class)
public ResponseEntity<ErrorDetails> handlePetFoodIdNotFoundException(PetFoodIdNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(PetFoodNameNotFoundException.class)
public ResponseEntity<ErrorDetails> handlePetFoodNameNotFoundException(PetFoodNameNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(PetFoodBrandNotFoundException.class)
public ResponseEntity<ErrorDetails> handlePetFoodBrandNotFoundException(PetFoodBrandNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(FoodInputInvalidException.class)
public ResponseEntity<ErrorDetails> handleFoodInputInvalidException(FoodInputInvalidException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.CONFLICT);
}



@ExceptionHandler(EmployeeNotFoundException.class)
public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(EmployeesNotFoundException.class)
public ResponseEntity<ErrorDetails> handleEmployeesNotFoundException(EmployeesNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(EmployeeInputInvalidException.class)
public ResponseEntity<ErrorDetails> handleEmployeeInputInvalidException(EmployeeInputInvalidException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.CONFLICT);


}

//User
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);


}
@ExceptionHandler(UserInputInvalidException.class)
public ResponseEntity<ErrorDetails> handleUserInputInvalidException(UserInputInvalidException ex, WebRequest req) {
    return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false)), HttpStatus.CONFLICT);


}
}
