package com.inventory.common.util.mail;

import org.springframework.stereotype.Component;

@Component("invmailsender")
public class InvMailSender/* extends JavaMailSenderImpl*/ {

	/*@Value("${spring.mail.password}")
	private String password;
	
	public InvMailSender() {
		super();
	}
	
	@Override
	public String getPassword() {
		String password = new String(Base64.getDecoder().decode(passoword.getBytes()));
		return password;
	}*/
}
