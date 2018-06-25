package com.inventory.web.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.service.entitlement.InvUserService;
import com.inventory.common.util.PasswordGenrator;

@Component
public class CustomInterceptor implements HandlerInterceptor {
	
	@Autowired
	private InvUserService invUSerService;
	
	@Autowired
	private PasswordGenrator passwordGenrator;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		System.out.println("Request Completed!");
	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		System.out.println("Method executed");
	}
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String useName = request.getHeader("userName");
		String password = request.getHeader("password");
		
		boolean isValidUser = false;
		InvUser user = invUSerService.findUserByEmail(useName);
		
		if(user == null) {
			PrintWriter writer = response.getWriter();
			writer.println("402 Unauthorized Access");
			writer.println("Requested user not register with system");
			return false;
		}
		
		if(user.getPassword() != null) {
			isValidUser  = passwordGenrator.matchPassword(password, user.getPassword());
		}
		
		if(!isValidUser) {
			PrintWriter writer = response.getWriter();
			writer.println("402 Unauthorized Access");
			writer.println("Please check provided credential. User Name OR Password wrong");
			return false;
		}
		
		return true;
	}
	
	
}
