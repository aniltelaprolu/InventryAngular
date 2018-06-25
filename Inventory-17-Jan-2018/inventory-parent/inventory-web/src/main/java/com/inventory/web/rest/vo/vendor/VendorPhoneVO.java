package com.inventory.web.rest.vo.vendor;

import javax.persistence.Column;

/**
 * @author ES002
 *
 */
public class VendorPhoneVO {
	private Long vendorId;
	
	private Long vendorPhoneId;
	
	private String addressType;
	
	private Long mobilePhonNo;
	
	private Long office1phonNo;
	
	private Long office2phonNo;
	
	

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

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

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
}
