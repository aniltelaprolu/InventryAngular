package com.inventory.common.util.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailUtil {

	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendTextMail(String toAddress) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("exceyonpb@gmail.com");
			helper.setTo(toAddress);
			helper.setText("How are you?");
			helper.setSubject("Hi");
			mailSender.send(message);
			logger.info("Successfully sent mail to : {}", toAddress);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while trying to send mail to : {}", toAddress, e);
		}
	}

	public void sendMail(String fromAddress, String toAddress, String subject, Context ctx, String templateName) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(fromAddress);
			helper.setTo(toAddress);
			helper.setSubject(subject);

			final String htmlContent = this.templateEngine.process(templateName, ctx);
			helper.setText(htmlContent, true);

			mailSender.send(message);
			logger.info("Successfully sent mail to : {}", toAddress);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while trying to send mail to : {}", toAddress, e);
		}
	}

	/**
	 * @param email
	 * @param pdfFile
	 */
	public void sendPurchaseDetails(String email, String pdfFile) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("exceyonpb@gmail.com");
			helper.setTo(email);
			helper.setSubject("Purchase Details");
			helper.setText("Please find below Attachment.");
			
			FileSystemResource file = new FileSystemResource(pdfFile);
			helper.addAttachment(file.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Successfully sent mail to : {}", email);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while trying to send mail to : {}", email, e);
		}
	}
}
