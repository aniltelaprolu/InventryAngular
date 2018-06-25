package com.inventory.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler  implements LogoutSuccessHandler {

	@SuppressWarnings("rawtypes")
	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication != null) {
			System.out.println(authentication.getName());
		}
		String sessionId = request.getSession().getId();
		request.getSession().invalidate();
		sessionRepository.delete(sessionId);
	}

}
