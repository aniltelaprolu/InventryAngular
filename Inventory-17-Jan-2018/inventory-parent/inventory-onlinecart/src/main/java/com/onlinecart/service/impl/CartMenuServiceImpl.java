package com.onlinecart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.modal.product.Category;
import com.inventory.common.modal.product.MenuGroup;
import com.inventory.common.modal.product.SubCategory;
import com.inventory.common.service.product.CategoryService;
import com.inventory.common.service.product.MenuGroupService;
import com.inventory.common.service.product.SubCategoryService;
import com.onlinecart.service.CartMenuService;
import com.onlinecart.vo.CartMenuVO;
import com.onlinecart.vo.CategoryVO;
import com.onlinecart.vo.SubCategoryVO;

@Service("cartMenuService")
public class CartMenuServiceImpl implements CartMenuService {

	@Autowired
	private MenuGroupService menuGroupService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Transactional
	public List<CartMenuVO> getAllMenus(){
		List<MenuGroup> cartMenus = menuGroupService.getAllMenus();
		List<CartMenuVO> cartMenuVOs = new ArrayList<CartMenuVO>();
		CartMenuVO cartMenuVO = null;
		for(MenuGroup cartMenu : cartMenus) {
			cartMenuVO = new CartMenuVO();
			cartMenuVO.setMenuGroupName(cartMenu.getMenuGroupName());
			cartMenuVO.setMenuGroupId(cartMenu.getMenuGroupId());
			cartMenuVOs.add(cartMenuVO);
		}
		
		return cartMenuVOs;
	}

	/* (non-Javadoc)
	 * @see com.onlinecart.service.CartMenuService#getAllCategory()
	 */
	@Transactional
	@Override
	public List<CategoryVO> getAllCategory() {
		 List<Category> listCategory = categoryService.getAllCategory();
		 
		 List<MenuGroup> listMenu = menuGroupService.getAllMenus();
		 List<CategoryVO> listVO = new ArrayList<>();
		 for(MenuGroup menu:listMenu) {
			 for(Category category:listCategory) {
				 if(menu.getMenuGroupId()==category.getMenuGroup().getMenuGroupId()) {
					 CategoryVO categoryVO = new CategoryVO();
					 BeanUtils.copyProperties(category, categoryVO);
					 categoryVO.setMenuGroupId(menu.getMenuGroupId());
					 listVO.add(categoryVO);
				 }
			 }
		 }
		 System.out.println(listVO);
		return listVO;
	}

	/* (non-Javadoc)
	 * @see com.onlinecart.service.CartMenuService#getAllSubCategory()
	 */
	@Transactional
	@Override
	public List<SubCategoryVO> getAllSubCategory() {
		List<MenuGroup> listMenu = menuGroupService.getAllMenus();
		 
		List<Category> listCategory = categoryService.getAllCategory();
		
		List<SubCategory> listSubCategory = subCategoryService.getAllSubCategory();
		 
		 List<SubCategoryVO> listVO = new ArrayList<>();
		 for(MenuGroup menu:listMenu) {
			 for(Category category:listCategory) {
				 if(menu.getMenuGroupId()==category.getMenuGroup().getMenuGroupId()) {
					 for(SubCategory subCategory:listSubCategory) {
						 if(category.getCategoryId()==subCategory.getCategory().getCategoryId()) {
							 SubCategoryVO subCategoryVO = new SubCategoryVO();
							 BeanUtils.copyProperties(subCategory, subCategoryVO);
							 subCategoryVO.setCategoryId(category.getCategoryId());
							 listVO.add(subCategoryVO);
						 }
					 }
				 }
			 }
		 }
		return listVO;
	}

}
