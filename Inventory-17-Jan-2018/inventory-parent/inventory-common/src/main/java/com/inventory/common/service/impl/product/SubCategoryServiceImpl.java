package com.inventory.common.service.impl.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.product.SubCategory;
import com.inventory.common.repository.product.SubCategoryRepository;
import com.inventory.common.service.product.SubCategoryService;

@Service("subCategoryService")
public class SubCategoryServiceImpl implements SubCategoryService {
	private static final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public SubCategory findBySubCategoryId(Long subCategoryId) {
		SubCategory subCategory =subCategoryRepository.getOne(subCategoryId);
		return subCategory;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.SubCategoryService#findAll(org.springframework.data.domain.PageRequest)
	 */
	@Override
	public List<SubCategory> findAll(PageRequest pageRequest) {
		logger.info("get all SubCategory ");
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<SubCategory> variantPage = subCategoryRepository.findAll(pageRequest);
		List<SubCategory> listVariant= variantPage.getContent();
		return listVariant;
	}

	@Override
	public SubCategory saveSubCategoryData(SubCategory subCategory) {
		SubCategory category=subCategoryRepository.save(subCategory);
		return category;
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.SubCategoryService#getAllSubCategory()
	 */
	@Override
	public List<SubCategory> getAllSubCategory() {
		return subCategoryRepository.findAll();
	}

}
