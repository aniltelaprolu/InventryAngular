package com.inventory.web.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views/dashboard")
public class DashBoardController {

	@RequestMapping(value={"/main"}, method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/dashboard/main");
		return modelAndView;
	}
	
	@RequestMapping(value={"/home"}, method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/dashboard/home");
		return modelAndView;
	}
}
