package com.github.wesleyegberto.uuidtokenauthentication.security.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.github.wesleyegberto.uuidtokenauthentication.security.entity.User;

@Singleton
public class UserAuthenticator {
	private Map<String, String> usersDatabase = new HashMap<>();
	
	@Inject
	private TokenManager tokenManager;
	
	@PostConstruct
	public void init() {
		usersDatabase.put("Wesley", "123456");
		usersDatabase.put("Odair", "123456");
	}
	
	public String validateUser(User user) throws AuthenticationException {
		Objects.requireNonNull(user, "The username and password are required");
		Objects.requireNonNull(user.getUsername(), "The username is required");
		Objects.requireNonNull(user.getPassword(), "The password is required");
		
		if(usersDatabase.containsKey(user.getUsername())) {
			if(user.getPassword().equals(usersDatabase.get(user.getUsername()))) {
				return tokenManager.createTokenFor(user.getUsername());
			}
		}
		throw new AuthenticationException("Invalid username and/or password");
	}
}
