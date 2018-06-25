package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.entitlement.RoleMenuItemAction;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuItemID;
import com.inventory.common.repository.entitlement.RoleMenuItemActionsRepository;
import com.inventory.common.service.entitlement.RoleMenuItemActionService;

@Service("roleMenuItemActionService")
public class RoleMenuItemActionServiceImpl implements RoleMenuItemActionService{

	private static final Logger logger = LoggerFactory.getLogger(RoleMenuItemActionServiceImpl.class);
	
	@Autowired
	private RoleMenuItemActionsRepository roleMenuItemActionRepository;
	
	@Override
	public List<RoleMenuItemAction> findByRoleMenuItemID(RoleMenuItemID roleMenuItemId) {
		logger.info("find all menu item actions by role menu item id : {}", roleMenuItemId);
		return roleMenuItemActionRepository.findByRoleMenuItemId(roleMenuItemId);
	}
	
}
