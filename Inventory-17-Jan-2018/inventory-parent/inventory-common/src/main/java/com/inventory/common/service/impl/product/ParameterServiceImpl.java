/**
 * 
 */
package com.inventory.common.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.modal.product.Parameter;
import com.inventory.common.repository.product.ParameterRepository;
import com.inventory.common.service.product.ParameterService;

/**
 * @author ES002
 *
 */

@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {
	
	@Autowired
	private ParameterRepository parameterRepository;

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.ParameterService#getAllData()
	 */
	@Override
	public List<Parameter> getAllData() {
		return parameterRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.inventory.common.service.product.ParameterService#getByParameterId(java.lang.Long)
	 */
	@Override
	public Parameter getByParameterId(Long parameterId) {
		return parameterRepository.getOne(parameterId);
	}

}
