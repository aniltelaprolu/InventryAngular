package com.inventory.web.rest.controller.vendor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorAddress;
import com.inventory.common.modal.vendor.VendorBankAccountDetails;
import com.inventory.common.modal.vendor.VendorPhone;
import com.inventory.common.service.VendorNotificationService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.common.service.vendor.VendorBankDetailsService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.vendor.InvVendorVO;
import com.inventory.web.rest.vo.vendor.VendorAddressVO;
import com.inventory.web.rest.vo.vendor.VendorBankDetailsVO;
import com.inventory.web.rest.vo.vendor.VendorPhoneVO;
import com.inventory.web.util.customId.CustomVendorId;

/**
 * @author ES002
 *
 */
@RestController
@RequestMapping("service/vendor")
public class InvVendorRestController {
	private static final Logger logger = LoggerFactory.getLogger(InvVendorRestController.class);

	@Autowired
	private InvVendorService vendorService;
	
	@Autowired
	private VendorNotificationService notificationService;
	
	@Autowired
	private CustomVendorId venId;
	
	@Autowired
	private VendorBankDetailsService vendorBankDetailsService;

	/**
	 * @param vendorVO
	 * @return Create A New Vendor
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<InvVendorVO>> saveVendor(@RequestBody InvVendorVO vendorVO) {
		logger.info("Creating Vendor : {}", vendorVO);
		System.out.println(vendorVO);
		ResponseVO<InvVendorVO> message = new ResponseVO<>();

		if (vendorVO == null) {
			message.setMessageId("BLANK_VENDOR");
			message.setMessageDetail("Vendor can not blank");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
		}
		try {
			InvVendor vendor = vendorService.findByEmail(vendorVO.getEmail());

			if (vendor != null) {
				logger.error("Unable to create. A Vendor with email {} already exist", vendorVO.getEmail());
				message.setMessageId("VENDOR_EXSIST");
				message.setMessageDetail("Vendor already exsist in the system");
				return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.CONFLICT);
			}

			InvVendor vendorData = new InvVendor();
			vendorData.setVenId(venId.generateVendorId(vendorVO));
			vendorData.setEmail(vendorVO.getEmail());
			vendorData.setName(vendorVO.getName());

			List<VendorAddressVO> listAddressVO = vendorVO.getVenAddress();
			VendorAddress vendorAddrs = null;
			List<VendorAddress> listAddrs = new ArrayList<>();
			for (VendorAddressVO addrsVO : listAddressVO) {
				vendorAddrs = new VendorAddress();
				vendorAddrs.setAddressId(addrsVO.getAddressId());
				vendorAddrs.setAddress(addrsVO.getAddress());
				vendorAddrs.setAddressType(addrsVO.getAddressType());
				vendorAddrs.setCity(addrsVO.getCity());
				vendorAddrs.setCountry(addrsVO.getCountry());
				vendorAddrs.setPincode(addrsVO.getPincode());
				vendorAddrs.setState(addrsVO.getState());
				listAddrs.add(vendorAddrs);
			}

			Set<VendorPhoneVO> listPhoneVO = vendorVO.getVenPhones();
			VendorPhone phone = null;
			Set<VendorPhone> listPhone = new HashSet<>();
			for (VendorPhoneVO phoneVO : listPhoneVO) {
				phone = new VendorPhone();
				phone.setVendorPhoneId(phoneVO.getVendorPhoneId());
				phone.setAddressType(phoneVO.getAddressType());
				phone.setMobilePhonNo(phoneVO.getMobilePhonNo());
				phone.setOffice1phonNo(phoneVO.getOffice1phonNo());
				phone.setOffice2phonNo(phoneVO.getOffice2phonNo());
				listPhone.add(phone);
			}
			
			List<VendorBankDetailsVO> listBankDetailsVO = vendorVO.getVendorBankDetails();
			VendorBankAccountDetails bankDetails= null;
			List<VendorBankAccountDetails> listBankDetails= new ArrayList<>();
			if(listBankDetailsVO!=null) {
				for(VendorBankDetailsVO bankDetailsVO:listBankDetailsVO) {
					bankDetails = new VendorBankAccountDetails();
					
					bankDetails.setVendorAccountId(bankDetailsVO.getVendorAccountId());
					bankDetails.setAccountHolderName(bankDetailsVO.getAccountHolderName());
					bankDetails.setBankName(bankDetailsVO.getBankName());
					bankDetails.setBranch(bankDetailsVO.getBranch());
					bankDetails.setAccountNumber(bankDetailsVO.getAccountNumber());
					bankDetails.setIfscCode(bankDetailsVO.getIfscCode());
					listBankDetails.add(bankDetails);
				}
			}
			vendorData.setVendorBankDetails(listBankDetails);
			vendorData.setVenAddress(listAddrs);
			vendorData.setPhones(listPhone);
			
			vendorService.saveVendorData(vendorData);
			

			logger.info("Vendor Data Saved Successfully");
			message.setMessageId("VENDOR_CREATED");
			message.setMessageDetail("Vendor created sucessfully");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occurred while saving Vendor Data");
			message.setMessageId("VENDOR_DATA_SAVE_FAILED");
			message.setMessageDetail("Exception occured while saving Vendor Data");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * @param vendorName
	 * @return Get Vendor By Name
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getByName/{vendorName}", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<InvVendorVO>> getVendorByName(@PathVariable("vendorName") String vendorName) {
		logger.info("Fetching Vendor with name {}", vendorName);

		ResponseVO<InvVendorVO> message = new ResponseVO<>();
		InvVendor vendor = vendorService.findByName(vendorName);

		if (vendor == null) {
			logger.info("Vendor name not found.", vendorName);
			message.setMessageId("VENDOR_NAME_NOT_FOUND");
			message.setMessageDetail("Vendor Data Not Found With the Name  " + vendorName);
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
		}
		try {
			if (vendor != null) {
				List<InvVendorVO> vendorVoData = new ArrayList<>();
				InvVendorVO vendorVO = new InvVendorVO();
				
				vendorVO.setVendorId(vendor.getVenderId());
				vendorVO.setEmail(vendor.getEmail());
				vendorVO.setName(vendor.getName());
				
				List<VendorAddress> listVenAddrs = vendor.getVenAddress();
				VendorAddressVO venAddrsVO = null;
				List<VendorAddressVO> listAddrsVO = new ArrayList<>();

				for (VendorAddress venAddrs : listVenAddrs) {
					venAddrsVO = new VendorAddressVO();
					BeanUtils.copyProperties(venAddrs, venAddrsVO);
					listAddrsVO.add(venAddrsVO);
				}

				Set<VendorPhone> listPhones = vendor.getPhones();
				VendorPhoneVO venPhoneVO = null;
				Set<VendorPhoneVO> listPhoneVO = new HashSet<>();
				for (VendorPhone venPhone : listPhones) {
					venPhoneVO = new VendorPhoneVO();
					BeanUtils.copyProperties(venPhone, venPhoneVO);
					listPhoneVO.add(venPhoneVO);
				}
				System.out.println(listAddrsVO);
				System.out.println(listPhoneVO);
				vendorVO.setVenAddress(listAddrsVO);
				vendorVO.setVenPhones(listPhoneVO);

				vendorVoData.add(vendorVO);

				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Data Found by Name");
				message.setData(vendorVoData);
				return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured while Searching Vendor By Name", e);
			message.setMessageId("EXCEPTION");
			message.setMessageDetail("Exception Occured while Searching Vendor By Name");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.FOUND);
	}

	/**
	 * @param vendorId
	 * @return get vendor by Id
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getById/{vendorId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<InvVendorVO>> getVendorById(@PathVariable("vendorId") Long vendorId) {
		logger.info("Fetching Vendor with id {}", vendorId);
		ResponseVO<InvVendorVO> message = new ResponseVO<>();
		InvVendor vendor = vendorService.findByVendorId(vendorId);

		if (vendor == null) {
			logger.info("Vendor name not found.", vendorId);
			message.setMessageId("VENDOR_ID_NOT_FOUND");
			message.setMessageDetail("Vendor Data Not Found With the ID  " + vendorId);
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
		}
		try {
			if (vendor != null) {
				List<InvVendorVO> vendorVoData = new ArrayList<>();
				InvVendorVO vendorVO = new InvVendorVO();

				vendorVO.setVendorId(vendor.getVenderId());
				vendorVO.setEmail(vendor.getEmail());
				vendorVO.setName(vendor.getName());

				List<VendorAddress> listVenAddrs = vendor.getVenAddress();
				VendorAddressVO venAddrsVO = null;
				List<VendorAddressVO> listAddrsVO = new ArrayList<>();

				for (VendorAddress venAddrs : listVenAddrs) {
					venAddrsVO = new VendorAddressVO();
					BeanUtils.copyProperties(venAddrs, venAddrsVO);
					listAddrsVO.add(venAddrsVO);
				}

				Set<VendorPhone> listPhones = vendor.getPhones();
				VendorPhoneVO venPhoneVO = null;
				Set<VendorPhoneVO> listPhoneVO = new HashSet<>();
				for (VendorPhone venPhone : listPhones) {
					venPhoneVO = new VendorPhoneVO();
					BeanUtils.copyProperties(venPhone, venPhoneVO);
					listPhoneVO.add(venPhoneVO);
				}
				System.out.println(listAddrsVO);
				System.out.println(listPhoneVO);
				vendorVO.setVenAddress(listAddrsVO);
				vendorVO.setVenPhones(listPhoneVO);

				vendorVoData.add(vendorVO);

				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Data Found by ID");
				message.setData(vendorVoData);
				return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured while Searching Vendor By ID", e);
			message.setMessageId("EXCEPTION");
			message.setMessageDetail("Exception Occured while Searching Vendor By ID");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.FOUND);

	}

	/**
	 * @param vendorId
	 * @return Delete Vendor By ID
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteById/{vendorId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<InvVendorVO>> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
		logger.info("Fetching & Deleting Vendor with id {}", vendorId);
		ResponseVO<InvVendorVO> message = new ResponseVO<>();
		InvVendor vendor = vendorService.findByVendorId(vendorId);

		if (vendor == null) {
			logger.info("Vendor Id not found.", vendorId);
			message.setMessageId("VENDOR_ID_NOT_FOUND");
			message.setMessageDetail("Vendor Data Not Found With The ID  " + vendorId);
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (vendor != null) {
				vendorService.DeleteVendorById(vendorId);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Deleted Successfully");
				return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);

			}
		} catch (Exception e) {
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error while deleting Vendor");
			return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
	}

	/**
	 * @return Get All Vendor
	 */

	@RequestMapping(value = "/getAllVendor", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<List<InvVendorVO>>> getAllVendor() {
		logger.info("Fetching All Vendor Details");

		ResponseVO<List<InvVendorVO>> message = new ResponseVO<>();

		try {

			List<InvVendor> invVendors = vendorService.getAllVendor();
			InvVendorVO invVendorVO = null;
			List<VendorAddress> listaddrs = null;
			VendorAddressVO venAddrsVO = null;
			List<VendorAddressVO> listAddrsVO = null;
			Set<VendorPhone> listPhones = null;
			VendorPhoneVO venPhoneVO = null;
			Set<VendorPhoneVO> listPhoneVO = null;

			List<InvVendorVO> invVendorVOs = new ArrayList<>();

			for (InvVendor invVendor : invVendors) {
				invVendorVO = new InvVendorVO();
				invVendorVO.setVendorId(invVendor.getVenderId());
				invVendorVO.setEmail(invVendor.getEmail());
				invVendorVO.setName(invVendor.getName());

				listaddrs = invVendor.getVenAddress();
				listAddrsVO = new ArrayList<>();
				for (VendorAddress venAddrs : listaddrs) {
					venAddrsVO = new VendorAddressVO();
					BeanUtils.copyProperties(venAddrs, venAddrsVO);
					listAddrsVO.add(venAddrsVO);
				}

				listPhones = invVendor.getPhones();
				listPhoneVO = new HashSet<>();
				for (VendorPhone venPhone : listPhones) {
					venPhoneVO = new VendorPhoneVO();
					BeanUtils.copyProperties(venPhone, venPhoneVO);
					listPhoneVO.add(venPhoneVO);
					invVendorVO.setVenPhones(listPhoneVO);
				}

				invVendorVO.setVenAddress(listAddrsVO);
				invVendorVOs.add(invVendorVO);
			}

			List<List<InvVendorVO>> listVo = new ArrayList<>();
			listVo.add(invVendorVOs);

			message.setMessageId("GET_ALL_VENDOR");
			message.setMessageDetail("Get All Vendor Details");
			message.setData(listVo);
			return new ResponseEntity<ResponseVO<List<InvVendorVO>>>(message, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while gatting all Vendor Data", e);
			message.setMessageId("GET_ALL_VENDOR_FAILED");
			message.setMessageDetail("Error occured while performing get all Vendor ");
			return new ResponseEntity<ResponseVO<List<InvVendorVO>>>(message, HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * @param vendorVO
	 * @return update Details
	 */

	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<InvVendorVO>> update(@RequestBody InvVendorVO vendorVO) {
		logger.info("Updating Vendor with id {}", vendorVO.getVendorId());
		ResponseVO<InvVendorVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<InvVendorVO>> response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
		InvVendor currentVendor = vendorService.findByVendorId(vendorVO.getVendorId());

		if (currentVendor == null) {
			message.setMessageId("VENDOR_BLANK");
			message.setMessageDetail("Vendor Details is Blank");
			response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {

			if (currentVendor != null) {
				currentVendor.setName(vendorVO.getName());
				currentVendor.setEmail(vendorVO.getEmail());

				List<VendorAddressVO> listAddressVO = vendorVO.getVenAddress();
				List<VendorAddress> currentListAddress = currentVendor.getVenAddress();

				Set<VendorPhoneVO> listPhoneVO = vendorVO.getVenPhones();
				Set<VendorPhone> currentListPhone = currentVendor.getPhones();

				for (VendorAddress currVenAddrs : currentListAddress) {
					for (VendorAddressVO addrsVO : listAddressVO) {
						if ((currVenAddrs.getAddressType()).equalsIgnoreCase(addrsVO.getAddressType())) {
							currVenAddrs.setAddress(addrsVO.getAddress());
							currVenAddrs.setAddressType(addrsVO.getAddressType());
							currVenAddrs.setCity(addrsVO.getCity());
							currVenAddrs.setCountry(addrsVO.getCountry());
							currVenAddrs.setPincode(addrsVO.getPincode());
							currVenAddrs.setState(addrsVO.getState());
						}
					}
				}
				
				for (VendorPhone currVenPhone : currentListPhone) {
					for (VendorPhoneVO phoneVO : listPhoneVO) {
						if ((currVenPhone.getAddressType()).equalsIgnoreCase(phoneVO.getAddressType())){
							currVenPhone.setMobilePhonNo(phoneVO.getMobilePhonNo());
							currVenPhone.setOffice1phonNo(phoneVO.getOffice1phonNo());
							currVenPhone.setOffice2phonNo(phoneVO.getOffice2phonNo());
						}
						}
					}
				vendorService.saveVendorData(currentVendor);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Updated Successfully");
				response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
				}else {
					message.setMessageId("VENDOR_BLANK");
					message.setMessageDetail("Vendor Details is Blank");
					response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured While Updating");
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Update Failed");
			response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/**
	 * @param searchRequestVO
	 * @return Vendor
	 */
	@RequestMapping(value = "/getAllVendorData", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<InvVendorVO>> getAll(/*@RequestBody SearchRequestVO searchRequestVO*/@RequestParam("pageNo")int pageNo,@RequestParam("pageSize")int pageSize) {
		logger.info("Fetching All Vendor Details");
		SearchResponseVO<InvVendorVO> message = new SearchResponseVO<>();
		ResponseEntity<SearchResponseVO<InvVendorVO>> response = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.OK);
		try {
			
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);

			List<InvVendor> invVendors = vendorService.findAll(pageRequest);

			List<InvVendorVO> invVendorVOs = new ArrayList<>();
			InvVendorVO vendorVO = null;
			List<VendorAddress> listAddrs = null;
			VendorAddressVO addrsVO = null;
			List<VendorAddressVO> listAddrsVO = null;
			List<VendorBankAccountDetails> listBankDetails = null;
			
			List<VendorBankDetailsVO> listBankDetailsVO = null;

			Set<VendorPhone> listPhones = null;
			VendorPhoneVO phoneVO = null;
			Set<VendorPhoneVO> listPhVO = null;
			if(invVendors!=null) {
				for (InvVendor vendor : invVendors) {
					vendorVO = new InvVendorVO();
					vendorVO.setEmail(vendor.getEmail());
					vendorVO.setName(vendor.getName());
					vendorVO.setVendorId(vendor.getVenderId());
					vendorVO.setVenId(vendor.getVenId());
					//vendorVO.setVendorKey(vendor.getVendorKey());
					listAddrs = vendor.getVenAddress();
					listAddrsVO = new ArrayList<>();
					if(listAddrs!=null) {
						for (VendorAddress addrs : listAddrs) {
							addrsVO = new VendorAddressVO();
							BeanUtils.copyProperties(addrs, addrsVO);
							listAddrsVO.add(addrsVO);
						}
					}
					listPhones = vendor.getPhones();
					listPhVO = new HashSet<>();
					if(listPhones!=null) {
						for (VendorPhone phones : listPhones) {
							phoneVO = new VendorPhoneVO();
							phoneVO.setMobilePhonNo(phones.getMobilePhonNo());
							phoneVO.setOffice1phonNo(phones.getOffice1phonNo());
							phoneVO.setOffice2phonNo(phones.getOffice2phonNo());
							listPhVO.add(phoneVO);
						}
					}
					listBankDetails = vendor.getVendorBankDetails();
					listBankDetailsVO = new ArrayList<>();
					VendorBankDetailsVO bankVO=null;
					if(listBankDetails!=null) {
						for(VendorBankAccountDetails bankAccountDetails:listBankDetails) {
							bankVO = new VendorBankDetailsVO();
							bankVO.setVendorId(vendor.getVenderId());
							BeanUtils.copyProperties(bankAccountDetails, bankVO);
							listBankDetailsVO.add(bankVO);
						}
					}
					vendorVO.setVendorBankDetails(listBankDetailsVO);
					vendorVO.setVenAddress(listAddrsVO);
					vendorVO.setVenPhones(listPhVO);
					invVendorVOs.add(vendorVO);
				}
				message.setMessageId("GET_ALL_VENDOR");
				message.setMessageDetail("All Vendor Details Found");
				message.setData(invVendorVOs);
				response = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.OK);
			}else {
				message.setMessageId("VENDOR_NOT_FOUND");
				message.setMessageDetail("Vendor Details Not Found");
				response = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			logger.error("Error occured while gatting all Vendor Data", e);
			message.setMessageId("GET_ALL_VENDOR_FAILED");
			message.setMessageDetail("Error occured while performing get all Vendor ");
			response = new ResponseEntity<SearchResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	
	/**
	 * @param vendorAddrsVO
	 * @return newAddress
	 */
	@RequestMapping(value = "/addNewAddrs", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VendorAddressVO>> AddNewAddress(@RequestBody VendorAddressVO vendorAddrsVO) {
		logger.info("Adding New Vendor Address In Vendor Id {}", vendorAddrsVO.getVendorId());
		ResponseVO<VendorAddressVO> message = new ResponseVO<>();

		InvVendor currentVendor = vendorService.findByVendorId(vendorAddrsVO.getVendorId());

		if (currentVendor == null) {
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Vendor Details Not Found For To Add New Address");
			return new ResponseEntity<ResponseVO<VendorAddressVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (currentVendor != null) {
				List<VendorAddress> listVenAddrs = currentVendor.getVenAddress();

				VendorAddress venAddrs = new VendorAddress();
				venAddrs.setAddress(vendorAddrsVO.getAddress());
				venAddrs.setAddressType(vendorAddrsVO.getAddressType());
				venAddrs.setCity(vendorAddrsVO.getCity());
				venAddrs.setCountry(vendorAddrsVO.getCountry());
				venAddrs.setPincode(vendorAddrsVO.getPincode());
				venAddrs.setState(vendorAddrsVO.getState());
				venAddrs.setAddressType(vendorAddrsVO.getAddressType());
				listVenAddrs.add(venAddrs);
			}
			vendorService.saveVendorData(currentVendor);
			message.setMessageId("VENDOR_FOUND");
			message.setMessageDetail("New Address Added Successfully");
			return new ResponseEntity<ResponseVO<VendorAddressVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured While Adding New Address");
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("New Address Add Failed");
			return new ResponseEntity<ResponseVO<VendorAddressVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * @param vendorphoneVO
	 * @return addNewPhone
	 */
	/*@RequestMapping(value = "/addNewPhone", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VendorPhoneVO>> addNewPhone(@RequestBody VendorPhoneVO vendorphoneVO) {
		logger.info("Adding New Vendor Phone In id {}", vendorphoneVO.getVendorId());
		ResponseVO<VendorPhoneVO> message = new ResponseVO<>();

		InvVendor currentVendor = vendorService.findByVendorId(vendorphoneVO.getVendorId());

		if (currentVendor == null) {
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Vendor Details Not Found For To Add New Phone");
			return new ResponseEntity<ResponseVO<VendorPhoneVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (currentVendor != null) {
				Set<VendorPhone> listVenPhones = currentVendor.getPhones();
				VendorPhone venPhone = new VendorPhone();
				venPhone.setPhoneType(vendorphoneVO.getPhoneType());
				venPhone.setPhonNo(vendorphoneVO.getPhonNo());
				listVenPhones.add(venPhone);
			}
			vendorService.saveVendorData(currentVendor);
			message.setMessageId("VENDOR_FOUND");
			message.setMessageDetail("Vendor Phone Successfully Added");
			return new ResponseEntity<ResponseVO<VendorPhoneVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured While Adding New Phone");
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Adding New Phone Failed");
			return new ResponseEntity<ResponseVO<VendorPhoneVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
	*/
	
	
	/**
	 * @param vendorVO
	 * @return notification
	 */
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<InvVendorVO>> sendMailToVendor(@RequestBody List<InvVendorVO> listVendor){
		logger.info("Sending Email Notification {}");
		ResponseVO<InvVendorVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<InvVendorVO>> response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
		if (listVendor == null) {
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Vendor Details Not Found To Send Notification");
			response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
		}
		
		try {
			if (listVendor != null) {
				InvVendor  vendor=null;
				for(InvVendorVO vendorData:listVendor) {
					vendor=new InvVendor();
					vendor.setEmail(vendorData.getEmail());
					vendor.setName(vendorData.getName());
				}
				notificationService.sendVendorNotification(vendor);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Details Found, Notification Sent Successfull");
				response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.OK);
			}else {
				message.setMessageId("BLANK_FOUND");
				message.setMessageDetail("Blank Details");
				response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exacption occured While Sending Email Notification {}");
			message.setMessageId("EXCEPTION");
			message.setMessageDetail("Exception Occured While Sending Notification");
			response = new ResponseEntity<ResponseVO<InvVendorVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
			
		return response;
		
	}
	
	
	/**
	 * 
	 * @param listBankDetails
	 * @return
	 */
	@RequestMapping(value = "/addBankAccountDetails", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VendorBankDetailsVO>> addBankAccountDetails(@RequestBody List<VendorBankDetailsVO> listBankDetails){
		logger.info("Add Bank Account Details {}");
		ResponseVO<VendorBankDetailsVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VendorBankDetailsVO>> response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);
		
		if (listBankDetails == null) {
			message.setMessageId("BLANK_DETAILS");
			message.setMessageDetail("Blank Details not Found");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
		}
		
		try {
			InvVendor vendor = vendorService.findByVendorId(listBankDetails.get(0).getVendorId());
			if (vendor != null) {
				List<VendorBankAccountDetails> listDetails = vendor.getVendorBankDetails();
				
				VendorBankAccountDetails accountDetails = null;
				for(VendorBankDetailsVO bankDetailsVO:listBankDetails) {
					accountDetails = new VendorBankAccountDetails();
					accountDetails.setAccountHolderName(bankDetailsVO.getAccountHolderName());
					accountDetails.setAccountNumber(bankDetailsVO.getAccountNumber());
					accountDetails.setBranch(bankDetailsVO.getBranch());
					accountDetails.setBankName(bankDetailsVO.getBankName());
					accountDetails.setIfscCode(bankDetailsVO.getIfscCode());
					listDetails.add(accountDetails);
				}
				
				vendor.setVendorBankDetails(listDetails);
				vendorService.saveVendorData(vendor);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Details Found, Bank Details Added Successfull");
			}
			else {
				message.setMessageId("VENDOR_NOT_FOUND");
				message.setMessageDetail("Vendor Details Not Found To Add Bank Details");
				response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exacption occurred While Adding bank Data {}");
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Exception Occured while Adding Bank Account");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @param bankDetailsVO
	 * @return
	 */

	@RequestMapping(value = "/updateBankAccountDetails", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<VendorBankDetailsVO>> updateBankAccountDetails(@RequestBody VendorBankDetailsVO bankDetailsVO){
		logger.info("Update Bank Account Details {}");
		ResponseVO<VendorBankDetailsVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VendorBankDetailsVO>> response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);
		
		if (bankDetailsVO == null) {
			message.setMessageId("BLANK_DETAILS");
			message.setMessageDetail("Blank Details not Found");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
		}
		
		try {
			InvVendor vendor = vendorService.findByVendorId(bankDetailsVO.getVendorId());
			
			if (vendor == null) {
				message.setMessageId("VENDOR_DETAILS_NOT_FOUND");
				message.setMessageDetail("Vendor Details not Found");
				response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
			}
			
			if (vendor != null) {
				List<VendorBankAccountDetails> listBankDetails = vendor.getVendorBankDetails();
					if(listBankDetails!=null) {
						for(VendorBankAccountDetails details:listBankDetails) {
							if(details.getVendorAccountId()==bankDetailsVO.getVendorAccountId()) {
								details.setVendorAccountId(bankDetailsVO.getVendorAccountId());
								details.setAccountHolderName(bankDetailsVO.getAccountHolderName());
								details.setAccountNumber(bankDetailsVO.getAccountNumber());
								details.setBranch(bankDetailsVO.getBranch());
								details.setBankName(bankDetailsVO.getBankName());
								details.setIfscCode(bankDetailsVO.getIfscCode());
							}
						}
					}
				vendorService.saveVendorData(vendor);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Details Found, Bank Details Update Successfull");
			}
			else {
				message.setMessageId("VENDOR_NOT_FOUND");
				message.setMessageDetail("Vendor Details Not Found To Update Bank Details");
				response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exacption occurred While Updating Bank Details {}");
			message.setMessageId("VENDOR_NOT_FOUND");
			message.setMessageDetail("Exception Occured while Updating Bank Account");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	

	
	/**
	 * 
	 * @param vendorId
	 * @return
	 */
	@RequestMapping(value = "/getBankDetailsByVendorId", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<VendorBankDetailsVO>> getBankDetailsByVendorId(/*@PathVariable("vendorId") Long vendorId*/@RequestParam("vendorId") Long vendorId) {
		logger.info("Fetching Vendor with id {}", vendorId);
		ResponseVO<VendorBankDetailsVO> message = new ResponseVO<>();
		InvVendor currentVendor = vendorService.findByVendorId(vendorId);
		
		ResponseEntity<ResponseVO<VendorBankDetailsVO>> response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);
		
		if (currentVendor == null) {
			logger.info("Vendor name not found.", vendorId);
			message.setMessageId("VENDOR_ID_NOT_FOUND");
			message.setMessageDetail("Vendor Data Not Found With the ID  " + vendorId);
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
		}
		try {
			if (currentVendor != null) {
				
				List<VendorBankAccountDetails> listBankDetails = currentVendor.getVendorBankDetails();
				VendorBankDetailsVO bankDetailsVO = null;
				List<VendorBankDetailsVO> listBankVO = new ArrayList<>();
				if(listBankDetails!=null) {
					for(VendorBankAccountDetails bankDetails : listBankDetails) {
						bankDetailsVO = new VendorBankDetailsVO();
						bankDetailsVO.setVendorId(vendorId);
						bankDetailsVO.setVendorAccountId(bankDetails.getVendorAccountId());
						bankDetailsVO.setAccountHolderName(bankDetails.getAccountHolderName());
						bankDetailsVO.setAccountNumber(bankDetails.getAccountNumber());
						bankDetailsVO.setBankName(bankDetails.getBankName());
						bankDetailsVO.setBranch(bankDetails.getBranch());
						bankDetailsVO.setIfscCode(bankDetails.getIfscCode());
						listBankVO.add(bankDetailsVO);
					}
					
					message.setMessageId("VENDOR_BANKDETAILS_FOUND");
					message.setMessageDetail("Vendor Bank Details Found");
					message.setData(listBankVO);
					response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured while Searching Vendor Bank Details By Vendor ID", e);
			message.setMessageId("EXCEPTION");
			message.setMessageDetail("Exception Occured while Searching Vendor Bank Details By ID");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	/**
	 * 
	 * @param vendorId
	 * @param vendorAccountId
	 * @return
	 */
	@RequestMapping(value = "/deletebankDetailsById", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseVO<VendorBankDetailsVO>> deletebankDetailsById(@RequestParam("vendorId") Long vendorId,@RequestParam("vendorAccountId") Long vendorAccountId) {
		logger.info("Fetching & Deleting Vendor Bank Details with id {}", vendorId);
		ResponseVO<VendorBankDetailsVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<VendorBankDetailsVO>> response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);
		InvVendor vendor = vendorService.findByVendorId(vendorId);

		if (vendor == null) {
			logger.info("Vendor Id not found.", vendorId);
			message.setMessageId("VENDOR_ID_NOT_FOUND");
			message.setMessageDetail("Vendor Data Not Found With The ID  " + vendorId);
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
		}

		try {
			if (vendor != null) {
				List<VendorBankAccountDetails> listBanks = vendor.getVendorBankDetails();
				
				VendorBankAccountDetails bankDetails = vendorBankDetailsService.findById(vendorAccountId);
				listBanks.remove(bankDetails);
				
				vendorService.saveVendorData(vendor);
				message.setMessageId("VENDOR_FOUND");
				message.setMessageDetail("Vendor Bank Deleted Successfully");
				response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.OK);

			}
			else {
				message.setMessageId("VENDOR_NOT_FOUND");
				message.setMessageDetail("Vendor Bank Details Not Found");
				response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error while deleting Vendor Bank Details");
			response = new ResponseEntity<ResponseVO<VendorBankDetailsVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
}
