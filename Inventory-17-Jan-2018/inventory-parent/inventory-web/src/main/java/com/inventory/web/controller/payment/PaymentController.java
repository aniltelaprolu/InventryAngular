/**
 * 
 */
package com.inventory.web.controller.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ES001
 *
 */
@Controller
@RequestMapping("views")
public class PaymentController {
		@RequestMapping(value={"/payment"}, method = RequestMethod.GET)
		public ModelAndView offers(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("views/payment");
			return modelAndView;
		}
		
		@RequestMapping(value={"/payment/createNewPayment"}, method = RequestMethod.GET)
		public ModelAndView createNewOffers(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/views/payment/createNewPayment");
			return modelAndView;
		}
}

