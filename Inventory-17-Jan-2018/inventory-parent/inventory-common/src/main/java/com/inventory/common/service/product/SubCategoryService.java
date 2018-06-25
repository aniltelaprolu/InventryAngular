package com.inventory.common.service.product;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.product.SubCategory;

public interface SubCategoryService {
	public SubCategory findBySubCategoryId(Long subCategoryId);
	public List<SubCategory> findAll(PageRequest pageRequest);
	public SubCategory saveSubCategoryData(SubCategory subCategory);
	public List<SubCategory> getAllSubCategory();
}
