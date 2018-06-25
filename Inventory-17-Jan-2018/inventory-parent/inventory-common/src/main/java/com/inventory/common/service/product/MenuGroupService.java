package com.inventory.common.service.product;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.product.MenuGroup;

public interface MenuGroupService {
	public MenuGroup saveMenuGroupData(MenuGroup menuGroup);
	public List<MenuGroup> findAll(PageRequest pageRequest);
	public List<MenuGroup> getAllMenus();
	public MenuGroup findByMenuGroupId(Long menuGroupId);
	public void deleteByMenuId(Long menuGroupId);
 }
