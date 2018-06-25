package com.inventory.common.modal.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="parameter")
public class Parameter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parameter_id")
	private Long parameterId;

	@Column(name="name")
	private String parameterName;
	
	@Column(name="options")
	private String parameterOptions;
	
	@OneToMany(targetEntity = VariantParameter.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "variant_id")
	private List<VariantParameter> variantParameters;

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
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * @return the parameterOptions
	 */
	public String getParameterOptions() {
		return parameterOptions;
	}

	/**
	 * @param parameterOptions the parameterOptions to set
	 */
	public void setParameterOptions(String parameterOptions) {
		this.parameterOptions = parameterOptions;
	}

	/**
	 * @return the variantParameters
	 */
	public List<VariantParameter> getVariantParameters() {
		return variantParameters;
	}

	/**
	 * @param variantParameters the variantParameters to set
	 */
	public void setVariantParameters(List<VariantParameter> variantParameters) {
		this.variantParameters = variantParameters;
	}
	
	
}












/*package com.inventory.common.modal.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parameter")
public class Parameter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parameter_id")
	private Long parameterId;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	@Column(name="length")
	private Long length;

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}
	
	
}
*/

















/*package com.inventory.common.modal.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parameter")
public class Parameter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parameter_id")
	private Long parameterId;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	@Column(name="length")
	private Long length;

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}
	
	
}
*/