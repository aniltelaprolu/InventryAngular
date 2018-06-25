/**
 * 
 */
package com.inventory.common.service.product;

import java.util.List;

import com.inventory.common.modal.product.Parameter;

/**
 * @author ES002
 *
 */
public interface ParameterService {
	public List<Parameter> getAllData();
	public Parameter getByParameterId(Long parameterId);
}
