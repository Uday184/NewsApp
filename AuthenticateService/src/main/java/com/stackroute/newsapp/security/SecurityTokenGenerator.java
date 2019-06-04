package com.stackroute.newsapp.security;

import java.util.Map;
import com.stackroute.newsapp.model.User;

public interface SecurityTokenGenerator {

	public Map<String, String> generateToken(User user);
	
}