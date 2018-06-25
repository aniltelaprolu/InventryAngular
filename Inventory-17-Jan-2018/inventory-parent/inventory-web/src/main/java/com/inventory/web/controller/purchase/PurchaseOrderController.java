/**
 * 
 */
package com.inventory.web.controller.purchase;

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
public class PurchaseOrderController {
	
	@RequestMapping(value={"/purchase"}, method = RequestMethod.GET)
	public ModelAndView purchase(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("views/purchase");
		return modelAndView;
	}
	
	@RequestMapping(value={"/purchase/purchase_order"}, method = RequestMethod.GET)
	public ModelAndView purchaseOrder(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/purchase/purchase_order");
		return modelAndView;
	}

}
