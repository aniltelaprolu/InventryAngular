/**
 * 
 */
package com.inventory.web.controller.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ES001
 *
 */
@Controller
@RequestMapping("views")
public class SettingController {
	@RequestMapping(value={"/setting"}, method = RequestMethod.GET)
	public ModelAndView setting(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/setting");
		return modelAndView;
	}
	
	
	@RequestMapping(value={"/company"}, method = RequestMethod.GET)
	public ModelAndView company(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/Setting/company");
		return modelAndView;
	}
}
