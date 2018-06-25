package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.entitlement.RoleMenuItem;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;
import com.inventory.common.repository.entitlement.RoleMenuItemRepository;
import com.inventory.common.service.entitlement.RoleMenuItemService;

@Service("roleMenuItemService")
public class RoleMenuItemServiceImpl implements RoleMenuItemService{

	private static final Logger logger = LoggerFactory.getLogger(RoleMenuItemServiceImpl.class);
	
	@Autowired
	private RoleMenuItemRepository roleMenuItemRepository;
	@Override
	public List<RoleMenuItem> findByRoleMenuID(RoleMenuID roleMenuId) {
		logger.info("find role menu items by role menu id : {}", roleMenuId);
		return roleMenuItemRepository.findByRoleMenuId(roleMenuId);
	}
	
}
