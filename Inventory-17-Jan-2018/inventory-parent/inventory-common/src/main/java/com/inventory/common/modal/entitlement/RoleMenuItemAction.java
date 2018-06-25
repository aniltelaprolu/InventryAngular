package com.inventory.common.modal.entitlement;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.modal.base.BaseAuditModel;
import com.inventory.common.modal.entitlement.compositekey.RoleMenuItemID;

@Entity
@Table(name = "role_menu_item_action")
public class RoleMenuItemAction extends BaseAuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454439332348735131L;

	@EmbeddedId
	private RoleMenuItemID roleMenuItemId;
	
	@ManyToOne
	@JoinColumn(name = "action_id")
	private Action action;

	/**
	 * @return the roleMenuItemId
	 */
	public RoleMenuItemID getRoleMenuItemId() {
		return roleMenuItemId;
	}

	/**
	 * @param roleMenuItemId the roleMenuItemId to set
	 */
	public void setRoleMenuItemId(RoleMenuItemID roleMenuItemId) {
		this.roleMenuItemId = roleMenuItemId;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
}
