package com.inventory.web.exception;

import com.inventory.common.modal.entitlement.InvUser;

public class FirstTimeLoginException extends BaseAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2964868514697640970L;

	private InvUser invUser;
	
	public FirstTimeLoginException(String message, InvUser invUser) {
		super(message);
		this.invUser = invUser;
	}

	public InvUser getInvUser() {
		return invUser;
	}

	public void setInvUser(InvUser invUser) {
		this.invUser = invUser;
	}
	
}
