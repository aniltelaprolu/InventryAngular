package com.inventory.web.rest.vo.vendor;

/**
 * @author ES002
 *
 */
public class VendorAddressVO {
	private Long vendorId;
	
	private Long addressId;

	/*private String addressLine1;
	
	private String addressLine2;
	
	private String addressLine3;*/
	
	private String address;
	
	private String pincode;

	private String city;

	private String state;
	
	private String country;
	
	private String addressType;
	
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "VendorAddressVO [vendorId=" + vendorId + ", addressId=" + addressId + ", address=" + address
				+ ", pincode=" + pincode + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", addressType=" + addressType + "]";
	}

}
