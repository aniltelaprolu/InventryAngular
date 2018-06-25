package com.inventory.web.rest.vo.entitlement;

public class InvUserPhoneVO {
	
	private Long vendorPhoneId;

	private String phoneType;

	private Long phonNo;

	public Long getVendorPhoneId() {
		return vendorPhoneId;
	}

	public void setVendorPhoneId(Long vendorPhoneId) {
		this.vendorPhoneId = vendorPhoneId;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public Long getPhonNo() {
		return phonNo;
	}

	public void setPhonNo(Long phonNo) {
		this.phonNo = phonNo;
	}
}
