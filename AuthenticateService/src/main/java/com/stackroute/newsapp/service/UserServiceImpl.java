package com.stackroute.newsapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {

		Optional<User> userResult = userRepository.findById(user.getUserId());

		if (userResult.isPresent()) {
			throw new UserAlreadyExistsException("User Already Exist");
		}

		userRepository.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password)throws UserNotFoundException {
		User authUser = userRepository.findByUserIdAndPassword(userId, password);
		if(authUser == null) {
			throw new UserNotFoundException("Invalid User Id and Password");
		}
		return authUser;
	}

}