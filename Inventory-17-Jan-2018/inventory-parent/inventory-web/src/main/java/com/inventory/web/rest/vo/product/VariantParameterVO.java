/**
 * 
 */
package com.inventory.web.rest.vo.product;

import com.inventory.common.constants.AppConstants.STATUS;

/**
 * @author ES002
 *
 */
public class VariantParameterVO {
	
	private Long parameterId;
	private Long variantParameterId;
	private String parameterValue;
	private STATUS parameterStatus;
	
	/**
	 * @return the parameterId
	 */
	public Long getParameterId() {
		return parameterId;
	}
	/**
	 * @param parameterId the parameterId to set
	 */
	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}
	/**
	 * @return the variantParameterId
	 */
	public Long getVariantParameterId() {
		return variantParameterId;
	}
	/**
	 * @param variantParameterId the variantParameterId to set
	 */
	public void setVariantParameterId(Long variantParameterId) {
		this.variantParameterId = variantParameterId;
	}
	/**
	 * @return the parameterValue
	 */
	public String getParameterValue() {
		return parameterValue;
	}
	/**
	 * @param parameterValue the parameterValue to set
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public STATUS getParameterStatus() {
		return parameterStatus;
	}
	public void setParameterStatus(STATUS parameterStatus) {
		this.parameterStatus = parameterStatus;
	}
	
}
