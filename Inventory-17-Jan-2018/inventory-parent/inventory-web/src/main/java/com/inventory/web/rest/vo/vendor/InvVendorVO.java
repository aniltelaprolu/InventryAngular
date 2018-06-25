package com.inventory.web.rest.vo.vendor;

import java.util.List;
import java.util.Set;

public class InvVendorVO {
	private Long vendorId;
	
	private String venId;
	
	private String email;
	
	private String name;
	
	private List<VendorAddressVO> venAddress;
	
	private Set<VendorPhoneVO> venPhones;
	
	private List<VendorBankDetailsVO> vendorBankDetails;
	

	public List<VendorBankDetailsVO> getVendorBankDetails() {
		return vendorBankDetails;
	}

	public void setVendorBankDetails(List<VendorBankDetailsVO> vendorBankDetails) {
		this.vendorBankDetails = vendorBankDetails;
	}

	public String getVenId() {
		return venId;
	}

	public void setVenId(String venId) {
		this.venId = venId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VendorAddressVO> getVenAddress() {
		return venAddress;
	}

	public void setVenAddress(List<VendorAddressVO> venAddress) {
		this.venAddress = venAddress;
	}

	public Set<VendorPhoneVO> getVenPhones() {
		return venPhones;
	}

	public void setVenPhones(Set<VendorPhoneVO> venPhones) {
		this.venPhones = venPhones;
	}

	@Override
	public String toString() {
		return "InvVendorVO [vendorId=" + vendorId + ", venId=" + venId + ", email=" + email + ", name=" + name
				+ ", venAddress=" + venAddress + ", venPhones=" + venPhones + "]";
	}

	
	
}
