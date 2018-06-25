package com.inventory.common.repository.entitlement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.RoleMenuItem;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;

@Repository("roleMenuItemRepository")
public interface RoleMenuItemRepository extends JpaRepository<RoleMenuItem, RoleMenuID> {
	List<RoleMenuItem> findByRoleMenuId(RoleMenuID roleMenuId);
}
