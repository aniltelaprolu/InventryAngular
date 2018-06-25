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

import com.inventory.common.constants.AppConstants.PHONETYPE;

@Entity
@Table(name = "inv_user_phone")
public class InvUserPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_id")
	private Long vendorPhoneId;

	@NotEmpty(message = "Please provide the phone type")
	@Column(name = "phone_type")
	@Enumerated(EnumType.STRING)
	private PHONETYPE phoneType;

	@NotEmpty(message = "Please provide the number")
	@Column(name = "phon_no")
	private Long phonNo;

	public Long getVendorPhoneId() {
		return vendorPhoneId;
	}

	public void setVendorPhoneId(Long vendorPhoneId) {
		this.vendorPhoneId = vendorPhoneId;
	}

	public PHONETYPE getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PHONETYPE phoneType) {
		this.phoneType = phoneType;
	}

	public Long getPhonNo() {
		return phonNo;
	}

	public void setPhonNo(Long phonNo) {
		this.phonNo = phonNo;
	}
}
