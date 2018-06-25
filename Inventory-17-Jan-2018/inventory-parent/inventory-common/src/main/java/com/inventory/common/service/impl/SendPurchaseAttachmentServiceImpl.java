/**
 * 
 */
package com.inventory.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.service.SendPurchaseAttachmentService;
import com.inventory.common.util.mail.MailUtil;

/**
 * @author ES002
 *
 */
@Service("sendPurchaseAttachmentService")
public class SendPurchaseAttachmentServiceImpl implements SendPurchaseAttachmentService {

	@Autowired
	private MailUtil mailUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.inventory.common.service.SendPurchaseAttachmentService#
	 * sendPurchaseAttachment(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendPurchaseAttachment(String email, String pdfFile) {
		mailUtil.sendPurchaseDetails(email, pdfFile);
	}

}
