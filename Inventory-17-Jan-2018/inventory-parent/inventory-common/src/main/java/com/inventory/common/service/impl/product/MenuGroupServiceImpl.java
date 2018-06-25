package com.inventory.common.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.product.MenuGroup;
import com.inventory.common.repository.product.MenuGroupRepository;
import com.inventory.common.service.product.MenuGroupService;

@Service("menuGroupService")
public class MenuGroupServiceImpl implements MenuGroupService {
	
	@Autowired
	private MenuGroupRepository menuGroupRepository;

	@Override
	public MenuGroup saveMenuGroupData(MenuGroup menuGroup) {
		MenuGroup menuGroups=menuGroupRepository.save(menuGroup);
		return menuGroups;
	}

	@Override
	public List<MenuGroup> findAll(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<MenuGroup> menuPage = menuGroupRepository.findAll(pageRequest);
		return menuPage.getContent();
		
	}

	@Override
	public List<MenuGroup> getAllMenus() {
		List<MenuGroup> listMenus = menuGroupRepository.findAll();
		return listMenus;
	}

	@Override
	public MenuGroup findByMenuGroupId(Long menuGroupId) {
		MenuGroup menuGroup=menuGroupRepository.findOne(menuGroupId);
		return menuGroup;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.MenuGroupService#deleteByMenuId(java.lang.Long)
	 */
	@Transactional
	@Override
	public void deleteByMenuId(Long menuGroupId) {
		menuGroupRepository.delete(menuGroupId);
	}
	
	

}
