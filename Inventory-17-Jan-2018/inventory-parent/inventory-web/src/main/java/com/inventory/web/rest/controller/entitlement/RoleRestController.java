package com.inventory.web.rest.controller.entitlement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.inventory.common.modal.entitlement.Action;
import com.inventory.common.modal.entitlement.Menu;
import com.inventory.common.modal.entitlement.MenuItem;
import com.inventory.common.modal.entitlement.Role;
import com.inventory.common.modal.entitlement.RoleMenuItem;
import com.inventory.common.modal.entitlement.RoleMenuItemAction;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuItemID;
import com.inventory.common.service.entitlement.MenuService;
import com.inventory.common.service.entitlement.RoleMenuItemActionService;
import com.inventory.common.service.entitlement.RoleMenuItemService;
import com.inventory.common.service.entitlement.RoleService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.entitlement.ActionVO;
import com.inventory.web.rest.vo.entitlement.MenuItemVO;
import com.inventory.web.rest.vo.entitlement.MenuVO;
import com.inventory.web.rest.vo.entitlement.RoleVO;

@RestController
@RequestMapping("/service/role")
public class RoleRestController {
	private static final Logger logger = LoggerFactory.getLogger(RoleRestController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMenuItemService roleMenuItemService;
	
	@Autowired
	private RoleMenuItemActionService roleMenuItemActionService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public ResponseEntity<ResponseVO<RoleVO>> getAll() {
		logger.info("start :: executing get all operation for roles");
		ResponseVO<RoleVO> message = new  ResponseVO<RoleVO>();
		try {
			
			List<Role> roles = roleService.findAll();
			
			List<RoleVO> roleVos = new ArrayList<RoleVO>();
			RoleVO roleVO = null;
			for(Role role : roles) {
				roleVO = getRoleVO(role);
				roleVos.add(roleVO);
			}
			
			message.setMessageId("GET_ALL_ROLES");
			message.setMessageDetail("get all actions");
			message.setData(roleVos);
			logger.info("end :: executing get all operation for roles");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all roles operation", e);
			message.setMessageId("GET_ALL_ROLES_FAILED");
			message.setMessageDetail("Error occured while performing get all roles operation");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/getDetail/{roleId}", method=RequestMethod.GET)
	public ResponseEntity<ResponseVO<RoleVO>> getRoleDetail(@PathVariable Integer roleId) {
		logger.info("start :: executing get all operation for roles");
		ResponseVO<RoleVO> message = new  ResponseVO<RoleVO>();
		try {
			
			Role role = roleService.findByRoleId(roleId);
			
			List<RoleVO> roleVos = new ArrayList<RoleVO>();
			RoleVO roleVO = getRoleVO(role);
			roleVos.add(roleVO);
			
			message.setMessageId("GET_ROLE_DETAILS");
			message.setMessageDetail("get role details");
			message.setData(roleVos);
			logger.info("end :: executing get role details operation for role");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get role details operation", e);
			message.setMessageId("GET_ROLE_DETAILS");
			message.setMessageDetail("Error occured while performing get role details operation");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseVO<RoleVO>> addNewRole(@RequestBody RoleVO roleObj) {
		logger.info("start :: executing add role operation");
		ResponseVO<RoleVO> message = new  ResponseVO<RoleVO>();
		try {
			
			roleService.addRole(roleObj.getRoleName());
			
			message.setMessageId("ROLE_ADD");
			message.setMessageDetail("role: " +roleObj.getRoleName()+" created successfully");
			logger.info("end :: executing add role operation");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing add role operation", e);
			message.setMessageId("ROLE_ADD");
			message.setMessageDetail("Error occured while performing add role operation");
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/delete/{roleId}", method=RequestMethod.GET)
	public ResponseEntity<ResponseVO<RoleVO>> deleteProductWithProductId(@PathVariable Integer roleId) {
		ResponseVO<RoleVO> message = new  ResponseVO<RoleVO>();
		try {
			roleService.updateEnableFlag(Boolean.FALSE, roleId);
			message.setMessageId("DEETE_ROLE");
			message.setMessageDetail("deleted role with role id : " + roleId);
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.ACCEPTED);
		}catch(Exception e) {
			logger.error("Error occured while performing delete role operation", e);
			message.setMessageId("DELETE_ROLE");
			message.setMessageDetail("Error occured while performing delete role operation for role id : " + roleId);
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value="/assignMenusAndActions", method=RequestMethod.POST)
	public ResponseEntity<ResponseVO<RoleVO>> assignMenusAndActions(@RequestBody RoleVO roleObj) {
		ResponseVO<RoleVO> message = new  ResponseVO<RoleVO>();
		try {
			Role role = roleService.findByRoleName(roleObj.getRoleName());
			if(role == null) {
				message.setMessageId("ASSIGN_MENUS_AND_ACTIONS");
				message.setMessageDetail("Error occured while performing assign menus and actions to role with role name : " + roleObj.getRoleName());
				return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
			}
			
			List<Integer> menuIds = roleObj.getMenus().stream().map(MenuVO :: getMenuId).collect(Collectors.toList());
			Set<Menu> menus = menuService.findByMenuIds(menuIds);
			role.setMenus(menus);
			roleService.updateRole(role);
			message.setMessageId("ASSIGN_MENUS_AND_ACTIONS");
			message.setMessageDetail("Assigned menus items and actions to role with role name : " + roleObj.getRoleName());
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.ACCEPTED);
		}catch(Exception e) {
			logger.error("Error occured while performing assigning menus items and actions to role with role name : {}", roleObj.getRoleName(), e);
			message.setMessageId("ASSIGN_MENUS_AND_ACTIONS");
			message.setMessageDetail("Error occured while performing assigning menus items and actions to role with role name : " + roleObj.getRoleName());
			return new ResponseEntity<ResponseVO<RoleVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * getRoleVO
	 * @param role
	 * @return
	 */
	private RoleVO getRoleVO(Role role) {
		RoleVO roleVO = new RoleVO();
		roleVO.setId(role.getId());
		roleVO.setEnable(role.getEnable());
		roleVO.setRoleName(role.getRoleName());
		roleVO.setCreatedBy(role.getCreatedBy());
		roleVO.setUpdatedBy(role.getUpdatedBy());
		Set<MenuVO> menuVos = new HashSet<MenuVO>();
		List<RoleMenuItem> roleMenuItems = null;
		Set<MenuItemVO> menuItemVos = null;
		List<RoleMenuItemAction> roleMenuItemActions = null;
		Set<ActionVO> menuItemActionVos = null;
		Set<Menu> menus = role.getMenus();
		Iterator<Menu> menuIterator = menus.iterator();
		Menu menu = null;
		MenuVO menuVO = null;
		MenuItem menuItem = null;
		MenuItemVO menuItemVO = null;
		Action menuItemAction = null;
		ActionVO menuItemActionVO = null;
		
		while(menuIterator.hasNext()) {
			menu = menuIterator.next();
			menuVO = new MenuVO();
			menuVO.setEnable(menu.getEnable());
			menuVO.setMenuId(menu.getMenuId());
			menuVO.setName(menu.getName());
			menuVO.setUrl(menu.getUrl());
			
			roleMenuItems = roleMenuItemService.findByRoleMenuID(new RoleMenuID(role.getId(), menu.getMenuId()));
			menuItemVos = new HashSet<MenuItemVO>();
			for(RoleMenuItem roleMenuItem : roleMenuItems) {
				menuItem = roleMenuItem.getMenuItem();
				menuItemVO = new MenuItemVO();
				menuItemVO.setEnable(menuItem.getEnable());
				menuItemVO.setMenuItemId(menuItem.getMenuItemId());
				menuItemVO.setMenuItemKey(menuItem.getMenuItemKey());
				menuItemVO.setName(menuItem.getName());
				menuItemVO.setUrl(menuItem.getUrl());
				
				roleMenuItemActions = roleMenuItemActionService.findByRoleMenuItemID(new RoleMenuItemID(role.getId(), menu.getMenuId(), menuItem.getMenuItemId()));
				menuItemActionVos = new HashSet<ActionVO>();
				for(RoleMenuItemAction roleMenuItemAction : roleMenuItemActions) {
					menuItemAction = roleMenuItemAction.getAction();
					menuItemActionVO = new ActionVO();
					menuItemActionVO.setAction(menuItemAction.getAction());
					menuItemActionVO.setActionId(menuItemAction.getActionId());
					menuItemActionVO.setActionKey(menuItemAction.getActionKey());
					menuItemActionVO.setEnableFlag(menuItemAction.getEnable());
					menuItemActionVO.setUrl(menuItemAction.getUrl());
					menuItemActionVos.add(menuItemActionVO);
				}
				menuItemVO.setActionVos(menuItemActionVos);
				menuItemVos.add(menuItemVO);
			}
			menuVO.setMenuItems(menuItemVos);
			menuVos.add(menuVO);
		}
		roleVO.setMenus(menuVos);
		return roleVO;
	}
}
