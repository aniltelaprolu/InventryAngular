package com.inventory.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.common.service.entitlement.InvUserService;

@Controller
@RequestMapping("/views")
public class UserController {

	@Autowired
	private InvUserService invUserService;
	
	@RequestMapping(value={"/users"}, method = RequestMethod.GET)
	public ModelAndView users(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/users");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/modalAddUser", method = RequestMethod.GET)
	public ModelAndView addUser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/user/modalAddUser");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/user/modalEditUser", method = RequestMethod.GET)
	public ModelAndView editUser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/user/modalEditUser");
		
		return modelAndView;
	}
	
	/*@RequestMapping(value="/user/userDetail", method = RequestMethod.GET)
	public ModelAndView userDetail(@RequestParam Long userId, HttpServletRequest request){
		System.out.println("user id : " + userId);
		InvUser invUser = invUserService.findUserById(userId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invUser", invUser);
		modelAndView.setViewName("/views/user/modalEditUser");
		
		return modelAndView;
	}*/
}
