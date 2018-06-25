package com.inventory.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.service.entitlement.InvUserService;
import com.inventory.web.exception.FirstTimeLoginException;

@Component
public class InvUserDetailsCheck implements UserDetailsChecker{

	@Autowired
	private InvUserService invUserService;
	
	public InvUserService getInvUserService() {
		return invUserService;
	}

	public void setInvUserService(InvUserService invUserService) {
		this.invUserService = invUserService;
	}

	@Override
	public void check(UserDetails userDetails) {
		InvUser invUser = invUserService.findUserByEmail(userDetails.getUsername());
		if(invUser == null) {
			throw new BadCredentialsException("Invalid Username/Password");
		}else if(invUser.isLocked()) {
			throw new LockedException("User is locked, please contact system administrator.");
		}else if(!USERSTATUS.ACTIVE.equals(invUser.getStatus())) {
			throw new DisabledException("User is not active, please contact system administrator.");
		}else if(invUser.isFirstTime()) {
			throw new FirstTimeLoginException("First time login , redirect to change password", invUser);
		}
	}
	
}
