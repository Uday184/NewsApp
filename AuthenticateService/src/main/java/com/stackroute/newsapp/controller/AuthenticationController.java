
package com.stackroute.newsapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.security.SecurityTokenGenerator;
import com.stackroute.newsapp.service.UserService;


@CrossOrigin

@RestController

@EnableWebMvc

@RequestMapping("/user")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		ResponseEntity<String> response;
		try {
			userService.saveUser(user);
			response = new ResponseEntity<String>("User created sucessfully", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
			response = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}

	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		ResponseEntity<?> response;
		try {
			if (user.getUserId() == null || user.getPassword() == null) {
				throw new UserNotFoundException("UserId and Password is Empty");
			}
			User userDetails = userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
			if (userDetails == null) {
				throw new UserNotFoundException("UserId and Password is Not found");
			}
			if (!(user.getPassword().equals(userDetails.getPassword()))) {
				throw new UserNotFoundException("UserId and Password is invalid");
			}
			response = new ResponseEntity<Map<String, String>>(securityTokenGenerator.generateToken(userDetails),
					HttpStatus.OK);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
}