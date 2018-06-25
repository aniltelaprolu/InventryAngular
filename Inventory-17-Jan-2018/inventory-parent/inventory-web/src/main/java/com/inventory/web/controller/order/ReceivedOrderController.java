/**
 * 
 */
package com.inventory.web.controller.order;

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
public class ReceivedOrderController {
	
	@RequestMapping(value={"/purchase/received_order"}, method = RequestMethod.GET)
	public ModelAndView purchaseOrder(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/purchase/received_order");
		return modelAndView;
	}
}
