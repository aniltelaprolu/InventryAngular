package com.inventory.common.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.product.Category;
import com.inventory.common.repository.product.CategoryRepository;
import com.inventory.common.service.product.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findByCategoryId(Long categoryId) {
		Category category = categoryRepository.getOne(categoryId);
		return category;
	}

	@Override
	public Category saveCategoryData(Category category) {
		Category category1 =categoryRepository.save(category);
		return category1;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.CategoryService#getAllCategory()
	 */
	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	
}
