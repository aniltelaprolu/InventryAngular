package com.inventory.web.controller.vendor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("views")
public class VendorController {
	
	@RequestMapping(value={"/vendors"}, method = RequestMethod.GET)
	public ModelAndView saveVendor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendors");
		return modelAndView;
	}
	
	@RequestMapping(value = "/vendor/modalAddVendor", method = RequestMethod.GET)
	public ModelAndView createNewVendor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendor/modalAddVendor");
		return modelAndView;
	}
	
	@RequestMapping(value = "/vendor/edit_vendor", method = RequestMethod.GET)
	public ModelAndView updateVendor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendor/edit_vendor");
		return modelAndView;
	}
	
	@RequestMapping(value = "/vendor/showvendor", method = RequestMethod.GET)
	public ModelAndView showVendor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendor/showvendor");
		return modelAndView;
	}
	
	@RequestMapping(value = "/vendor/add_vendor_bank_details", method = RequestMethod.GET)
	public ModelAndView add_vendor_bank_details() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendor/add_vendor_bank_details");
		return modelAndView;
	}
	
	@RequestMapping(value = "/vendor/edit_bank", method = RequestMethod.GET)
	public ModelAndView updateVendorBank() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/vendor/edit_bank");
		return modelAndView;
	}
}