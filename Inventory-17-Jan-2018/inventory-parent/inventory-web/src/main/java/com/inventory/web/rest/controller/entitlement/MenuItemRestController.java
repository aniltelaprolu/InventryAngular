package com.inventory.web.rest.controller.entitlement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.entitlement.MenuItem;
import com.inventory.common.service.entitlement.MenuItemService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchRequestVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.entitlement.MenuItemVO;

@RestController
@RequestMapping("/service/menuItem")
public class MenuItemRestController {

	private static final Logger logger = LoggerFactory.getLogger(MenuItemRestController.class);
	
	@Autowired
	private MenuItemService menuItemService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public ResponseEntity<ResponseVO<MenuItemVO>> getAll() {
		logger.info("start :: executing get all operation for menu items");
		ResponseVO<MenuItemVO> message = new  ResponseVO<MenuItemVO>();
		try {
			
			List<MenuItem> menuItems = menuItemService.findAll();
			
			List<MenuItemVO> menuItemVos = new ArrayList<MenuItemVO>();
			MenuItemVO menuItemVO = null;
			for(MenuItem menuItem : menuItems) {
				menuItemVO = new MenuItemVO();
				menuItemVO.setEnable(menuItem.getEnable());
				menuItemVO.setMenuItemId(menuItem.getMenuItemId());
				menuItemVO.setMenuItemKey(menuItem.getMenuItemKey());
				menuItemVO.setName(menuItem.getName());
				menuItemVO.setUrl(menuItem.getUrl());
				menuItemVos.add(menuItemVO);
			}
			
			message.setMessageId("GET_ALL_MENU_ITEMS");
			message.setMessageDetail("get all menu items");
			message.setData(menuItemVos);
			logger.info("end :: executing get all operation for menu items");
			return new ResponseEntity<ResponseVO<MenuItemVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all menu items operation", e);
			message.setMessageId("GET_ALL_MENU_ITEMS_FAILED");
			message.setMessageDetail("Error occured while performing get all menu items operation");
			return new ResponseEntity<ResponseVO<MenuItemVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/getAll/{menuId}", method=RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<MenuItemVO>> getAllByEnableFlag(@PathVariable Integer menuId, @RequestBody SearchRequestVO searchRequestVO) {
		logger.info("start :: executing get all operation for menu item using menu id {}", menuId);
		SearchResponseVO<MenuItemVO> message = new  SearchResponseVO<MenuItemVO>();
		try {
			List<MenuItem> menuItems = menuItemService.findByMenuAndEnableFlag(menuId, Boolean.TRUE);
			
			List<MenuItemVO> menuItemVos = new ArrayList<MenuItemVO>();
			MenuItemVO menuItemVO = null;
			for(MenuItem menuItem : menuItems) {
				menuItemVO = new MenuItemVO();
				menuItemVO.setEnable(menuItem.getEnable());
				menuItemVO.setMenuItemId(menuItem.getMenuItemId());
				menuItemVO.setMenuItemKey(menuItem.getMenuItemKey());
				menuItemVO.setName(menuItem.getName());
				menuItemVO.setUrl(menuItem.getUrl());
				menuItemVos.add(menuItemVO);
			}
			
			message.setMessageId("GET_MENU_ITEM_USING_MENU_ID");
			message.setMessageDetail("get all menu items with menu id : " + menuId);
			message.setData(menuItemVos);
			logger.info("end :: executing get all operation for menu item using menu id {}", menuId);
			return new ResponseEntity<SearchResponseVO<MenuItemVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all operation for menu item using menu id", e);
			message.setMessageId("GET_MENU_ITEM_USING_MENU_ID_FAILED");
			message.setMessageDetail("Error occured while performing get all operation for menu item using menu id");
			return new ResponseEntity<SearchResponseVO<MenuItemVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<MenuItemVO>> updateAction(@RequestBody List<MenuItemVO> menuItemVos) {
		logger.info("start :: update service for menu items executing");
		ResponseVO<MenuItemVO> message = new  ResponseVO<MenuItemVO>();

		if(menuItemVos == null ) {
			message.setMessageId("BLANK_Menu_ITEMS");
			message.setMessageDetail("MenuItemVos can not be blank");
			return new ResponseEntity<ResponseVO<MenuItemVO>>(message, HttpStatus.METHOD_NOT_ALLOWED);
		}

		StringBuilder messageStr = new StringBuilder("enable flag updated for menu item keys: ");
		
		try {
			for(MenuItemVO menuItemVo : menuItemVos) {
				String menuItemKey = menuItemVo.getMenuItemKey();
				menuItemService.updateEnableFlag(menuItemVo.getEnable(), menuItemKey);
				messageStr.append(menuItemKey + ",");
			}
			message.setMessageId("MENU_ITEMS_UPDATED");
			message.setMessageDetail(messageStr.toString());
			logger.info("end :: update service for menu items executing");
			return new ResponseEntity<ResponseVO<MenuItemVO>>(message, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error occured while performing update menu items operation", e);
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error occured while updating enable flag, total no of updated flag message: " +messageStr.toString());
			return new ResponseEntity<ResponseVO<MenuItemVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
