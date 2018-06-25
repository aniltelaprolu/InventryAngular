package com.inventory.common.service.product;

import java.util.List;

import com.inventory.common.modal.product.Category;
import com.inventory.common.modal.product.MenuGroup;

public interface CategoryService {
	public Category findByCategoryId(Long categoryId);
	public Category saveCategoryData(Category category);
	public List<Category> getAllCategory();
} 
