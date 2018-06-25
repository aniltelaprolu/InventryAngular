package com.inventory.web.controller.custom.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inventory.web.exception.custom.ProductNotFoundException;

@ControllerAdvice(value="com.inventory")
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("errMsg", exception.getMessage());
		return "globalErrorHandlerPage";
	}
}
