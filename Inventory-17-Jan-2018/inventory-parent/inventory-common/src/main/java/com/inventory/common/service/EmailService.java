package com.inventory.common.service;

import com.inventory.common.modal.entitlement.InvUser;

public interface EmailService {
	public void sendNewUserRegistrationMail(InvUser invUser);
}
