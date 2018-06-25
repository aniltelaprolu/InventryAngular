package com.inventory.common.repository.entitlement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.RoleMenuItemAction;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuItemID;

@Repository("roleMenuItemActionsRepository")
public interface RoleMenuItemActionsRepository extends JpaRepository<RoleMenuItemAction, RoleMenuItemID> {
	List<RoleMenuItemAction> findByRoleMenuItemId(RoleMenuItemID roleMenuItemId);
}
