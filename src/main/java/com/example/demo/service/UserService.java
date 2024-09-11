package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptionHandler.user.UserInputInvalidException;
import com.example.demo.exceptionHandler.user.UserNotFoundException;

public interface UserService {
	User findUserByUserNamePassword(String username,String password)throws UserNotFoundException ;
	void createuser(User user) throws UserInputInvalidException;

}
