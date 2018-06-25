package com.inventory.common.service.impl.entitlement;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.entitlement.Menu;
import com.inventory.common.repository.entitlement.MenuRepository;
import com.inventory.common.service.entitlement.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public Menu findByName(String menuName) {
		logger.info("find menu by menu name : {}", menuName);
		return menuRepository.findByName(menuName);
	}

	@Override
	public Menu findByMenuId(Integer menuId) {
		logger.info("find menu by menu id : {}", menuId);
		return menuRepository.findByMenuId(menuId);
	}

	@Override
	public Set<Menu> findByMenuIds(List<Integer> menuIds) {
		logger.info("find menu by menu ids : {}", menuIds);
		return menuRepository.findByMenuIds(menuIds);
	}
	
}

