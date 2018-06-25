package com.inventory.common.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.service.EmailService;
import com.inventory.common.util.mail.MailUtil;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private MailUtil mailUtils;
	
	@Override
	public void sendNewUserRegistrationMail(InvUser invUser) {
		logger.info("Sending mail after new registration for user {}", invUser.getName());
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("name", invUser.getName());
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		
		mailUtils.sendMail("exceyonpb@gmail.com", invUser.getEmail(), "Welcome Onboard : " + invUser.getName(), ctx, "newregistration");
	}

}
