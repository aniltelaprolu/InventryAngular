package com.inventory.common.service.impl;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.service.VendorNotificationService;
import com.inventory.common.util.mail.MailUtil;

@Service("vendorNotification")
public class VendorNotificationServiceImpl implements VendorNotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorNotificationServiceImpl.class);

	@Autowired
	private MailUtil mailUtils;
	
	@Override
	public void sendVendorNotification(InvVendor vendor) {
		
		logger.info("Sending notification to vendor {}", vendor.getName());
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("name", vendor.getName());
		ctx.setVariable("subscriptionDate", new Date());
		
		mailUtils.sendMail("exceyonpb@gmail.com", vendor.getEmail(), "Welcome  : " + vendor.getName(), ctx, "vendor");
		//mailUtils.sendTextMail(vendor.getEmail());
	}

}
