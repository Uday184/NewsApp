
package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.security.SecurityTokenGenerator;
import com.stackroute.newsapp.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private SecurityTokenGenerator securityTokenGenrator;

	private User user;

	@InjectMocks
	private AuthenticationController userController;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("uday", "pass", "uday", "chatterjee", null, "A");
		
	}

	@After
	public void tearDown() {
		user = null;
	}

	/**
	 * this method convert movie object into json
	 * 
	 * @param movie
	 * @return
	 */
	private String jsonToString(User user) {
		Gson gson = new Gson();
		String result = gson.toJson(user);
		System.out.println(result);
		return result;
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(userService.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isCreated());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
 
	@Test
	public void testLoginUser()throws Exception {
		when(userService.findByUserIdAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());
		verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
		
	} 
}