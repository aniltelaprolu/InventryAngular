package com.inventory.common.modal.entitlement.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleMenuItemID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 595910989012661594L;

	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_item_id")
	private Integer menuItemId;

	public RoleMenuItemID() {
		
	}
	
	public RoleMenuItemID(Integer roleId, Integer menuId, Integer menuItemId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
		this.menuItemId = menuItemId;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @return the menuItemId
	 */
	public Integer getMenuItemId() {
		return menuItemId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((menuItemId == null) ? 0 : menuItemId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleMenuItemID other = (RoleMenuItemID) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (menuItemId == null) {
			if (other.menuItemId != null)
				return false;
		} else if (!menuItemId.equals(other.menuItemId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleMenuItemID [roleId=" + roleId + ", menuId=" + menuId + ", menuItemId=" + menuItemId + "]";
	}
}
