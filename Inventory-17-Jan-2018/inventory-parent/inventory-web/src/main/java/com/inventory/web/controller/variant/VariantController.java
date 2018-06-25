/**
 * 
 */
package com.inventory.web.controller.variant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 
 *
 */
@Controller
@RequestMapping("views")
public class VariantController {
	

	@RequestMapping(value="/product/addNewVariant", method = RequestMethod.GET)
	public ModelAndView addVariant(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/addNewVariant");
		return modelAndView;
	}
	

	@RequestMapping(value="/showVariants", method = RequestMethod.GET)
	public ModelAndView showVariants(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/showVariants");
		return modelAndView;
	}
	
	@RequestMapping(value="/product/update_variant", method = RequestMethod.GET)
	public ModelAndView updateVariants(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/update_variant");
		return modelAndView;
	}
	
}
