package com.onlinecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinecart.service.CartMenuService;

@Controller
public class HomePageController {

	@Autowired
	private CartMenuService cartMenuService;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("menus", cartMenuService.getAllMenus());
		modelAndView.addObject("categories", cartMenuService.getAllCategory());
		modelAndView.addObject("subcategories", cartMenuService.getAllSubCategory());
		modelAndView.addObject("title", "Home");
		modelAndView.addObject("ifClickHome", true);
		return modelAndView;
	}
	
	
}
