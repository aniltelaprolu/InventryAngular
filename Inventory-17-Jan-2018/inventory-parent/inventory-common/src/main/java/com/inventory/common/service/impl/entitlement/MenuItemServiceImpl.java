package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.entitlement.MenuItem;
import com.inventory.common.repository.entitlement.MenuItemRepository;
import com.inventory.common.service.entitlement.MenuItemService;

@Service("menuItemService")
public class MenuItemServiceImpl implements MenuItemService{

	private static final Logger logger = LoggerFactory.getLogger(MenuItemServiceImpl.class);
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Override
	public MenuItem findByMenuItemKeyAndEnable(String menuItemKey, Boolean enable) {
		logger.info("find menu item by menu item key {} and enable flag value {}", menuItemKey, enable);
		return menuItemRepository.findByMenuItemKeyAndEnable(menuItemKey, enable);
	}

	@Override
	public void updateEnableFlag(Boolean enable, String menuItemKey) {
		logger.info("update enable flag with value {} for menu item key {}", enable, menuItemKey);
		menuItemRepository.updateEnableFlag(enable, menuItemKey);
	}

	@Override
	public List<MenuItem> findByMenuAndEnableFlag(Integer menuId, Boolean enableFlag) {
		logger.info("find all menu items by flag {} for menu {}", enableFlag, menuId);
		return menuItemRepository.findByMenuAndEnable(menuId, enableFlag);
	}

	@Override
	public List<MenuItem> findAll() {
		logger.info("find all menuItems");
		return menuItemRepository.findAll();
	}
	
}
