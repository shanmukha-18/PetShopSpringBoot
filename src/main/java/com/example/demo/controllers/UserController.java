package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exceptionHandler.user.UserInputInvalidException;
import com.example.demo.exceptionHandler.user.UserNotFoundException;
import com.example.demo.service.UserServiceImpl;

@RestController
@RequestMapping("api/v1/user")  // Base path for user-related endpoints
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS from Angular development server
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;  // Service for user operations
	
	// Retrieve a user by username and password and return HTTP status 200 OK
	@GetMapping("/get/{username}/{password}")
	public ResponseEntity<User>getUser(@PathVariable String username,@PathVariable String password) throws UserNotFoundException{
		return new ResponseEntity<User>(userServiceImpl.findUserByUserNamePassword(username,password), HttpStatus.OK);
	}
	
	// Create a new user and return HTTP status 201 Created
	@PostMapping("/add")
	public ResponseEntity<Void>createUser(@RequestBody User user) throws UserInputInvalidException{
		userServiceImpl.createuser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
