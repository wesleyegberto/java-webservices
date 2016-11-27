package com.github.wesleyegberto.uuidtokenauthentication.security.control;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Singleton;

@Singleton
public class TokenManager {
	private static ZoneOffset DEFAULT_ZONE = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).getOffset(); 
	// Storage of Token and Expiration time
	private Map<String, Long> tokensDatabase = new HashMap<>();
	
	public String createTokenFor(String username) {
		String simpleToken = UUID.randomUUID().toString();
		long expiration = LocalDateTime.now().plusMinutes(20).toEpochSecond(DEFAULT_ZONE);
		tokensDatabase.put(simpleToken, expiration);
		return simpleToken;
	}

	public boolean isTokenValid(String authToken) {
		if(tokensDatabase.containsKey(authToken)) {
			return !isExpired(tokensDatabase.get(authToken));
		}
		return false;
	}
	
	public boolean isExpired(long expireTime) {
		return LocalDateTime.now().toEpochSecond(DEFAULT_ZONE) > expireTime;
	}
}
