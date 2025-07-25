package com.watsoo.wallet.service;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	Boolean validateToken(String token, UserDetails userDetails);

	String extractUsername(String token);
	
	public String generateToken(String username)  ;
}
