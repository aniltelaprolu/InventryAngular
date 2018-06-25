package com.inventory.web.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.service.entitlement.InvUserService;

@Controller
public class LoginController {
	
	@Autowired
	private InvUserService userService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private SessionRepository sessionRepository;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		InvUser invUser = new InvUser();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && !"anonymousUser".equalsIgnoreCase(auth.getName())) {
			invUser = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("user", invUser);
			modelAndView.setViewName("home");
		}else {
			modelAndView.addObject("user", invUser);
			modelAndView.setViewName("login");
		}
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		InvUser user = new InvUser();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid InvUser user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		InvUser userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
			modelAndView.addObject("user", new InvUser());
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new InvUser());
			modelAndView.setViewName("registration");			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(InvUser user){
		System.out.println("called service");
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		InvUser invUser = userService.findUserByEmail(auth.getName());
		if(invUser != null) {
			modelAndView.addObject("userName", "Welcome " + invUser.getName() + " " + invUser.getLastName() + " (" + invUser.getEmail() + ")");
			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
			modelAndView.setViewName("home");
		}else {
			modelAndView.addObject("errorMsg", "No User found with email id : " + user.getEmail());
			modelAndView.addObject("user", new InvUser());
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}
	
	/*@RequestMapping(value={"/logout"}, method = RequestMethod.GET)
	public ModelAndView logout(){
		ModelAndView modelAndView = new ModelAndView();
		InvUser invUser = new InvUser();
		modelAndView.addObject("user", invUser);
		modelAndView.setViewName("login");
		return modelAndView;
	}*/
	
	@RequestMapping(value="/admin/modal-addform", method = RequestMethod.GET)
	public ModelAndView addProduct(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/modal-addform");
		
		return modelAndView;
	}
}
