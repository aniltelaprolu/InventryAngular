package com.inventory.common.service.entitlement;

import java.util.List;

import com.inventory.common.modal.entitlement.RoleMenuItem;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;

public interface RoleMenuItemService {
	List<RoleMenuItem> findByRoleMenuID(RoleMenuID roleMenuId);
}
