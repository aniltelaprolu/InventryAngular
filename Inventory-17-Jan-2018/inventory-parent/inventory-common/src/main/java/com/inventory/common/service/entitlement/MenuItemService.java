package com.inventory.common.service.entitlement;

import java.util.List;

import com.inventory.common.modal.entitlement.MenuItem;

public interface MenuItemService {
	MenuItem findByMenuItemKeyAndEnable(String menuItemKey, Boolean enable);
	void updateEnableFlag(Boolean enable, String menuItemKey);
	List<MenuItem> findByMenuAndEnableFlag(Integer menuId, Boolean enable);
	List<MenuItem> findAll();
}
