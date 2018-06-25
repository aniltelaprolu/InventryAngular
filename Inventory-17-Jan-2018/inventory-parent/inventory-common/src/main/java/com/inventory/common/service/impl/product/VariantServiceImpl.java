package com.inventory.common.service.impl.product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.repository.product.VariantRepository;
import com.inventory.common.service.product.VariantService;

@Service
public class VariantServiceImpl implements VariantService {
	
	private static final Logger logger = LoggerFactory.getLogger(VariantServiceImpl.class);
	
	@Autowired
	private VariantRepository variantRepository;

	@Override
	public Set<Variant> findAll(PageRequest pageRequest) {
		logger.info("get all variants ");
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<Variant> variantPage = variantRepository.findAll(pageRequest);
		List<Variant> listVariant= variantPage.getContent();
		Set<Variant> setPage=new HashSet<>(listVariant);
		return setPage;
	}

	@Transactional
	@Override
	public void deleteByVariantId(Long variantId) {
		logger.info("delete variant by id {}", variantId);
		if(variantId!=null) {
			variantRepository.deleteByVariantId(variantId);
		}
	}

	@Override
	public Variant findByVariantId(Long  variantId) {
		logger.info("find variant by id {}", variantId);
		Variant variant=null;
		if(variantId!=null)
		variant = variantRepository.findByVariantId(variantId);
		return variant;
	}

	@Transactional
	@Override
	public void updateVariantStatus(STATUS variantStatus, Long variantId) {
		variantRepository.updateVariantStatus(variantStatus, variantId);
	}
}
