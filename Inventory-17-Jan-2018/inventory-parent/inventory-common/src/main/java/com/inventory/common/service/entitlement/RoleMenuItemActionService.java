package com.inventory.common.service.entitlement;

import java.util.List;

import com.inventory.common.modal.entitlement.RoleMenuItemAction;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuItemID;

public interface RoleMenuItemActionService {
	List<RoleMenuItemAction> findByRoleMenuItemID(RoleMenuItemID roleMenuItemId);
}
