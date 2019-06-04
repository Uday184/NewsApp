package com.stackroute.newsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repository.UserRepository;


@RunWith(SpringRunner.class)
public class ServiceImplTest {


	@Mock
	private UserRepository repository;
	
	private User user;
	
	@InjectMocks
	private UserServiceImpl userService; 
	
	Optional<User> mockResult;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("uday", "pass", "uday", "chatterjee", new Date(02,07,2018), "A"); 
		mockResult = Optional.of(user);
	}
	
	@After
	public void tearDown() {
		user = null;
		mockResult = null;
	}  
	
	@Test
	public void testRegisterUser() throws Exception{
		when(repository.save(Mockito.any(User.class))).thenReturn(user);
		boolean ind =userService.saveUser(user);
		assertTrue("unable to register user", ind);
		verify(repository, times(1)).save(user);
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testRegisterFailure() throws Exception{
		when(repository.findById(user.getUserId())).thenReturn(mockResult);
		when(repository.save(Mockito.any(User.class))).thenReturn(user);
		userService.saveUser(user);
	}

	@Test 
	public void testValidationSucces() throws Exception{
		when(repository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User result = repository.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(result);
		assertEquals(user.getUserId(), result.getUserId());
		verify(repository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testValidationFailure() throws Exception{
		when(repository.findById("tora")).thenReturn(null);
		userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}