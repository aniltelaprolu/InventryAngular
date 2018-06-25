package com.inventory.common.service.entitlement;

import java.util.List;
import java.util.Set;

import com.inventory.common.modal.entitlement.Menu;

public interface MenuService {
	Menu findByName(String menuName);
	Menu findByMenuId(Integer menuId);
	Set<Menu> findByMenuIds(List<Integer> menuIds);
}
