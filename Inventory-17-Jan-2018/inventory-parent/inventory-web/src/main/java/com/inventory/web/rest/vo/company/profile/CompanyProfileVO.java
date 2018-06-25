/**
 * 
 */
package com.inventory.web.rest.vo.company.profile;

import java.util.List;

/**
 * @author ES002
 *
 */
public class CompanyProfileVO {
	
	private Long companyId;

	private String companyName;
	
	private String gstNumber;
	
	private String email;
	
	private String website;

	private String description;
	
	private Long phone;
	
	private List<CompanyBankVO> companyBank;
	
	private List<CompanyAddressVO> companyAddress;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public List<CompanyBankVO> getCompanyBank() {
		return companyBank;
	}

	public void setCompanyBank(List<CompanyBankVO> companyBank) {
		this.companyBank = companyBank;
	}

	public List<CompanyAddressVO> getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(List<CompanyAddressVO> companyAddress) {
		this.companyAddress = companyAddress;
	}
	
}
