package com.inventory.web.controller.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("views")
public class CategoryController {
	
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public ModelAndView getCategoriesForm(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/categories");
		return modelAndView;
	}
	
	@RequestMapping(value="/product/add_menu_details", method = RequestMethod.GET)
	public ModelAndView addCategoriesDetails(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/add_menu_details");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/product/add_new_category", method = RequestMethod.GET)
	public ModelAndView addNewCategory(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/add_new_category");
		return modelAndView;
	}
	
	@RequestMapping(value="/product/add_new_subcategory", method = RequestMethod.GET)
	public ModelAndView addNewSubCategory(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/add_new_subcategory");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/product/update_categories_details", method = RequestMethod.GET)
	public ModelAndView updateCategoriesDetails(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/product/update_categories_details");
		return modelAndView;
	}
}
