package com.stackroute.newsapp.service;

import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.model.User;

public interface UserService {

	public boolean saveUser(User user)throws UserAlreadyExistsException;
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}