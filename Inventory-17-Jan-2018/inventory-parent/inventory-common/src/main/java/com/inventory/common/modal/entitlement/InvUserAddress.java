package com.inventory.common.modal.entitlement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.inventory.common.constants.AppConstants.ADDRESSTYPE;
import com.inventory.common.modal.base.BaseAuditModel;

@Table(name = "inv_user_address")
@Entity
public class InvUserAddress extends BaseAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3215112523372344591L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long addressId;

	@NotEmpty(message = "Please provide the Address")
	@Column(name = "address_line1")
	private String addressLine1;
	
	@NotEmpty(message = "Please provide the Address")
	@Column(name="address_line2")
	private String addressLine2;
	
	@Column(name="address_line3")
	private String addressLine3;
	
	@NotEmpty(message = "Please provide the pincode")
	@Column(name="pincode")
	private String pincode;
	
	@NotEmpty(message = "Please provide the City")
	@Column(name="city")
	private String city;

	@NotEmpty(message = "Please provide the State")
	@Column(name="state")
	private String state;
	
	@NotEmpty(message = "Please provide the Country")
	@Column(name="country")
	private String country;
	
	@NotEmpty(message = "Please provide the Address type")
	@Column(name = "address_type")
	@Enumerated(EnumType.STRING)
	private ADDRESSTYPE addressType;

	/**
	 * @return the addressId
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**
	 * @param addressLine3 the addressLine3 to set
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the addressType
	 */
	public ADDRESSTYPE getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(ADDRESSTYPE addressType) {
		this.addressType = addressType;
	}
	
}
