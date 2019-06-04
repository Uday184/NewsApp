package com.stackroute.newsapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.newsapp.model.User;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepositoryTest{

	@Autowired
	private UserRepository repository;
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User("uday", "pass", "uday", "chatterjee", new Date(02,07,2018), "A"); 
	}
	
	@After
	public void tearDown() {
		user = null;
	}  
	
	@Ignore
	@Test
	public void testRegisterUser() {
		repository.save(user);
		Optional<User> result = repository.findById(user.getUserId());
		assertThat(result.equals(user));
	}
	
	
}