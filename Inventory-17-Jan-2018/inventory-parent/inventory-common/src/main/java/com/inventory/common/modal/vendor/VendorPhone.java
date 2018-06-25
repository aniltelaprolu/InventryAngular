package com.inventory.common.modal.vendor;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vendor_phone")
public class VendorPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Vendor_phone_id")
	private Long vendorPhoneId;
/*
	@Column(name = "phone_type")
	private String phoneType;*/
	
	@Column(name="address_type")
	private String addressType;

	@Column(name = "mobile_no")
	private Long mobilePhonNo;
	
	@Column(name = "office1")
	private Long office1phonNo;
	
	@Column(name = "office2")
	private Long office2phonNo;

	public Long getVendorPhoneId() {
		return vendorPhoneId;
	}

	public void setVendorPhoneId(Long vendorPhoneId) {
		this.vendorPhoneId = vendorPhoneId;
	}

	public Long getMobilePhonNo() {
		return mobilePhonNo;
	}

	public void setMobilePhonNo(Long mobilePhonNo) {
		this.mobilePhonNo = mobilePhonNo;
	}

	public Long getOffice1phonNo() {
		return office1phonNo;
	}

	public void setOffice1phonNo(Long office1phonNo) {
		this.office1phonNo = office1phonNo;
	}

	public Long getOffice2phonNo() {
		return office2phonNo;
	}

	public void setOffice2phonNo(Long office2phonNo) {
		this.office2phonNo = office2phonNo;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(mobilePhonNo);
	}
	
}
