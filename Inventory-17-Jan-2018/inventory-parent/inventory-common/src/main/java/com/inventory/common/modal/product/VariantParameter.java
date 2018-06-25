package com.inventory.common.modal.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.base.BaseAuditModel;

@Entity
@Table(name="variant_parameter")
public class VariantParameter extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1890361211318294541L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="variant_parameter_id")
	private Long variantParameterId;
	
	@Column(name="parameter_value")
	private String value;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private STATUS status;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="parameter_id")
	private Parameter parameter;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="variant_id")
	private Variant variant;
	

	/**
	 * @return the status
	 */
	public STATUS getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(STATUS status) {
		this.status = status;
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
	 * @return the parameter
	 */
	public Parameter getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the variant
	 */
	public Variant getVariant() {
		return variant;
	}

	/**
	 * @param variant the variant to set
	 */
	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
