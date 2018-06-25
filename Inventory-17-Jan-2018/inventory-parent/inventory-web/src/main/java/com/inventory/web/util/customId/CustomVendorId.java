package com.inventory.web.util.customId;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.rest.vo.vendor.InvVendorVO;
import com.inventory.web.rest.vo.vendor.VendorAddressVO;

@Component
public class CustomVendorId {

	@Autowired
	private InvVendorService service;

	public static String drawDigitsFromString(String strValue) {
		String str = strValue.trim();
		String digits = "";
		for (int i = 0; i < str.length(); i++) {
			char chrs = str.charAt(i);
			if (Character.isDigit(chrs))
				digits = digits + chrs;
		}
		return digits;
	}

	public String generateVendorId(@RequestBody InvVendorVO vendorVO) {
		InvVendor ven = service.findVendor();
		String generatedId = null;
		if (ven != null) {
			String venId = ven.getVenId();

			System.out.println("Vendor Id " + venId);
			String id = CustomVendorId.drawDigitsFromString(venId);
			Integer convertedId = Integer.parseInt(id);
			convertedId = convertedId + 1;
			String fconvertedId = String.valueOf(convertedId);
			for (int i = 0; id.length() > fconvertedId.length(); i++) {
				fconvertedId = "0" + fconvertedId;
			}
			generatedId =generateId(vendorVO) + fconvertedId;;

		} else {
			generatedId = generateId(vendorVO)+"001";
		}
		return generatedId;
	}
	private String generateId(InvVendorVO vendorVO){

		List<VendorAddressVO> addrsVO = vendorVO.getVenAddress();
		Map<String, String> countryListData = new HashMap<String, String>();

		String country = "";
		String state = "";
		String countryData = "";
		for (VendorAddressVO addrs : addrsVO) {
			country = addrs.getCountry();
			state = addrs.getState();
			break;
		}

		String[] locales = Locale.getISOCountries();

		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);

			String Shortcountry = obj.getCountry();
			String countryList = obj.getDisplayCountry();

			countryListData.put(countryList, Shortcountry);
			countryData = countryListData.get(country);
		}

		Map<String, String> states = new HashMap<String, String>();

		states.put("Andhra Pradesh", "AP");
		states.put("Kerala", "KL");
		states.put("Maharashtra", "MH");
		states.put("Tamil Nadu", "TN");
		states.put("Punjab", "PB");
		states.put("Telangana", "TG");

		return countryData + states.get(state) ;
	}
}
