package com.inventory.web.controller.chart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views")
public class ChartController {

	@RequestMapping(value={"/chart"}, method = RequestMethod.GET)
	public ModelAndView chart(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/chart");
		return modelAndView;
	}
}
