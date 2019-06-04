package com.stackroute.newsapp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.stackroute.newsapp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, String> jwtTokenMap = new HashMap<>();
		jwtTokenMap.put("token", jwtToken);
		jwtTokenMap.put("userType", user.getUserType());
		jwtTokenMap.put("message", "user sucessfully logged in");
		return jwtTokenMap;
	}

}