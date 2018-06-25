package com.inventory.web.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("views")
public class ProductController {
	

	@RequestMapping(value={"/products"}, method = RequestMethod.GET)
	public ModelAndView products(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/products");
		return modelAndView;
	}
	
	@RequestMapping(value="/product/addNewProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/addNewProduct");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/product/updateProduct", method = RequestMethod.GET)
	public ModelAndView updateProduct(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/updateProduct");
		return modelAndView;
	}
	
	
}
