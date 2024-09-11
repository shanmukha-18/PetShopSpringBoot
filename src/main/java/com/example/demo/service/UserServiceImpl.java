package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exceptionHandler.user.UserInputInvalidException;
import com.example.demo.exceptionHandler.user.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	 
	// Fetch user based on username and password from the repository.
	@Override
	public User findUserByUserNamePassword(String username,String password)throws UserNotFoundException{
		// TODO Auto-generated method stub
	return	userRepository.findByUsernameAndPassword(username,password);
		
	}
	
	   // Save the user entity to the repository.
    // If there are any issues with user input, UserInputInvalidException is thrown.
	@Override
	public void createuser(User user) throws UserInputInvalidException{
		// TODO Auto-generated method stub
		userRepository.save(user);
		
	}

}
