/**
 * 
 */
package com.inventory.web.controller.offers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ES002
 *
 */
@Controller
@RequestMapping("views")
public class OfferController {
		@RequestMapping(value={"/offers"}, method = RequestMethod.GET)
		public ModelAndView offers(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("views/offers");
			return modelAndView;
		}
		
		@RequestMapping(value={"/offers/createNewOffers"}, method = RequestMethod.GET)
		public ModelAndView createNewOffers(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/views/offers/createNewOffers");
			return modelAndView;
		}
		
		@RequestMapping(value={"/offers/update_offers"}, method = RequestMethod.GET)
		public ModelAndView updateOffers(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/views/offers/update_offers");
			return modelAndView;
		}
		
}
