package com.inventory.common.util;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenrator {

	static final String PWD_CHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public String generatePassword() {
		StringBuilder sb = new StringBuilder( 5 );
		   for( int i = 0; i < 5; i++ ) 
		      sb.append( PWD_CHAR.charAt( rnd.nextInt(PWD_CHAR.length()) ) );
		   return sb.toString();
	}
	
	public String getEncrptedPassword(String password) {
		return bCryptPasswordEncoder().encode(password);
	}
	
	public boolean matchPassword(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder().matches(rawPassword, encodedPassword);
		
	}
}
