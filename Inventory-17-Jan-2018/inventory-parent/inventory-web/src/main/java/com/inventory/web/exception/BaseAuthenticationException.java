package com.inventory.web.exception;

import org.springframework.security.core.AuthenticationException;

public class BaseAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseAuthenticationException(String message) {
		super(message);
	}

}
