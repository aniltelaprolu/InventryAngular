package com.inventory.common.modal.entitlement;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;

@Entity
@Table(name = "role_menu_item")
public class RoleMenuItem extends BaseAuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454439332348735131L;

	@EmbeddedId
	private RoleMenuID roleMenuId;
	
	@ManyToOne
	@JoinColumn(name = "menu_item_id")
	private MenuItem menuItem;

	/**
	 * @return the roleMenuId
	 */
	public RoleMenuID getRoleMenuId() {
		return roleMenuId;
	}

	/**
	 * @param roleMenuId the roleMenuId to set
	 */
	public void setRoleMenuId(RoleMenuID roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	/**
	 * @return the menuItem
	 */
	public MenuItem getMenuItem() {
		return menuItem;
	}

	/**
	 * @param menuItem the menuItem to set
	 */
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
}
