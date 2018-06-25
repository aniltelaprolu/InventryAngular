package com.onlinecart.service;

import java.util.List;

import com.onlinecart.vo.CartMenuVO;
import com.onlinecart.vo.CategoryVO;
import com.onlinecart.vo.SubCategoryVO;

public interface CartMenuService {
	public List<CartMenuVO> getAllMenus();
	
	public List<CategoryVO> getAllCategory();
	
	public List<SubCategoryVO> getAllSubCategory();
	
}
