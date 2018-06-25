package com.inventory.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.inventory.common.service.entitlement.InvUserService;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	private InvUserService invUserService;

	@Override
	public Authentication authenticate(Authentication authentication)
          throws AuthenticationException {

	  try {

		Authentication auth = super.authenticate(authentication);

		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts
		invUserService.resetFailedAttempt(authentication.getName());

		return auth;

	  } catch (BadCredentialsException e) {
		//invalid login, update to user_attempts
		invUserService.updateFailedAttempt(authentication.getName());
		throw e;
	  }
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
	}
}
