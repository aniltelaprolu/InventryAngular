/**
 * 
 */
package com.inventory.web.rest.controller.company.profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.modal.company.profile.CompanyAddress;
import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.company.profile.CompanyProfile;
import com.inventory.common.service.company.profile.CompanyBankService;
import com.inventory.common.service.company.profile.CompanyProfileService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.company.profile.CompanyAddressVO;
import com.inventory.web.rest.vo.company.profile.CompanyBankVO;
import com.inventory.web.rest.vo.company.profile.CompanyProfileVO;

/**
 * @author ES002
 *
 */
@RestController
@RequestMapping("service/companyprofile")
public class CompanyProfileRestController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyProfileRestController.class);

	@Autowired
	private CompanyProfileService companyProfileService;

	@Autowired
	private CompanyBankService companyBankService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllCompanyBankDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<CompanyBankVO>> getCompanyBankDetails() {
		logger.info("Retriving  Details: {}");

		ResponseVO<CompanyBankVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<CompanyBankVO>> response = new ResponseEntity<ResponseVO<CompanyBankVO>>(HttpStatus.OK);

		try {

			List<CompanyBank> listBankDetails = companyBankService.getAllBankDetails();

			if (listBankDetails == null || listBankDetails.isEmpty()) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				response = new ResponseEntity<ResponseVO<CompanyBankVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listBankDetails != null) {
				List<CompanyBankVO> listComboVO = new ArrayList<>();

				listBankDetails.forEach(companyBank -> {
					CompanyBankVO companyBankVO = new CompanyBankVO();
					BeanUtils.copyProperties(companyBank, companyBankVO);
					listComboVO.add(companyBankVO);
				});

				message.setMessageId("NUMBER_FOUND");
				message.setMessageDetail("Found!");
				message.setData(listComboVO);
				response = new ResponseEntity<ResponseVO<CompanyBankVO>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Data");
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving Data");
			response = new ResponseEntity<ResponseVO<CompanyBankVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCompanyDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<CompanyProfileVO>> companyProfileDetails() {
		logger.info("Retriving  Details of CompanySetting: {}");

		ResponseVO<CompanyProfileVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<CompanyProfileVO>> response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(HttpStatus.OK);

		try {

			CompanyProfile companyprofile = companyProfileService.getdetails();

			if (companyprofile == null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Company Details Not Found!");
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (companyprofile != null) {
				CompanyProfileVO CompanyProfileVO = new CompanyProfileVO();
				BeanUtils.copyProperties(companyprofile, CompanyProfileVO);

				message.setMessageId("FOUND");
				message.setMessageDetail("Found!");
				message.setData(Arrays.asList(CompanyProfileVO));
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Data");
			message.setMessageId("COMPANY_DETAILS_NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving Company Data");
			response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	

	/**
	 * 
	 * @param CompanyProfileVO
	 * @return
	 */
	@RequestMapping(value = "/updateCompanyProfile", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<CompanyProfileVO>> updateCompanyDetails(
			@RequestBody CompanyProfileVO companyProfileVO) {
		logger.info("Retriving  Details of CompanyProfile: {}");

		ResponseVO<CompanyProfileVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<CompanyProfileVO>> response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(HttpStatus.OK);

		try {

			if (companyProfileVO == null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (companyProfileVO != null) {
				CompanyProfile companyprofile = null;
				if (companyProfileVO.getCompanyId() != null) {
					companyprofile = companyProfileService.findByCompanyId(companyProfileVO.getCompanyId());
				} else {
					companyprofile = new CompanyProfile();
				}
				companyprofile.setCompanyName(companyProfileVO.getCompanyName());
				companyprofile.setEmail(companyProfileVO.getEmail());
				companyprofile.setPhone(companyProfileVO.getPhone());
				companyprofile.setGstNumber(companyProfileVO.getGstNumber());
				companyprofile.setWebsite(companyProfileVO.getWebsite());
				companyprofile.setDescription(companyProfileVO.getDescription());

				List<CompanyAddressVO> listAddrsVO = companyProfileVO.getCompanyAddress();
				if (listAddrsVO != null) {
					for(CompanyAddressVO addrsVo :listAddrsVO) {
						CompanyAddress addrs = new CompanyAddress();
						BeanUtils.copyProperties(addrsVo, addrs);
						companyprofile.getCompanyAddress().add(addrs);
					}
				}
				List<CompanyBankVO> listBankVO = companyProfileVO.getCompanyBank();
				if (listBankVO != null) {
					for(CompanyBankVO bankVO :listBankVO) {
						CompanyBank bank = new CompanyBank();
						BeanUtils.copyProperties(bankVO, bank);
						companyprofile.getCompanyBank().add(bank);
					}
				}
				companyProfileService.saveCompanyDetails(companyprofile);

				message.setMessageId("FOUND");
				message.setMessageDetail("Found!");
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Updating Data");
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Exception occured while Updating Data");
			response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	
	
	/**
	 * 
	 * @param companyProfileVO
	 * @return
	 */
	@RequestMapping(value = "/updateCompanyProfileDetails", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<CompanyProfileVO>> updateCompanyProfileDetails(@RequestBody CompanyProfileVO companyProfileVO) {
		logger.info("Retriving  Details of CompanyProfile: {}");

		ResponseVO<CompanyProfileVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<CompanyProfileVO>> response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(HttpStatus.OK);

		try {

			if (companyProfileVO == null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (companyProfileVO != null) {
				CompanyProfile companyprofile = null;
				if (companyProfileVO.getCompanyId() != null) {
					companyprofile = companyProfileService.findByCompanyId(companyProfileVO.getCompanyId());
				} else {
					companyprofile = new CompanyProfile();
				}
				companyprofile.setCompanyName(companyProfileVO.getCompanyName());
				companyprofile.setEmail(companyProfileVO.getEmail());
				companyprofile.setPhone(companyProfileVO.getPhone());
				companyprofile.setGstNumber(companyProfileVO.getGstNumber());
				companyprofile.setWebsite(companyProfileVO.getWebsite());
				companyprofile.setDescription(companyProfileVO.getDescription());

				companyProfileService.saveCompanyDetails(companyprofile);

				message.setMessageId("FOUND");
				message.setMessageDetail("Found!");
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.OK);
			}else {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Saving Or Updating Data");
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Exception occured while Saving Or Updating Data");
			response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	
	
	@RequestMapping(value = "/updateCompanyAddressDetails", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<CompanyProfileVO>> updateCompanyAddressDetails(@RequestBody CompanyProfileVO companyProfileVO) {
		logger.info("Retriving  Details of CompanyProfile: {}");

		ResponseVO<CompanyProfileVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<CompanyProfileVO>> response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(HttpStatus.OK);

		try {

			if (companyProfileVO == null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (companyProfileVO != null) {
				CompanyProfile companyprofile = null;
				if (companyProfileVO.getCompanyId() != null) {
					companyprofile = companyProfileService.findByCompanyId(companyProfileVO.getCompanyId());
				} else {
					companyprofile = new CompanyProfile();
				}

				List<CompanyAddressVO> listAddrsVO = companyProfileVO.getCompanyAddress();
				List<CompanyAddress> listAddrs = companyprofile.getCompanyAddress();
				
				if (listAddrsVO != null) {
						for(CompanyAddressVO addrsVo :listAddrsVO) {
							if(listAddrs.isEmpty()) {
								for(CompanyAddress addrs:listAddrs) {
									if(addrsVo.getAddressId()!=null||addrs.getAddressId()!=null) {
										if(addrsVo.getAddressId()==addrs.getAddressId()) {
											BeanUtils.copyProperties(addrsVo, addrs);
											listAddrs.add(addrs);
										}
									}
								}
							}
							else {
								CompanyAddress addrs = new CompanyAddress();
								BeanUtils.copyProperties(addrsVo, addrs);
								listAddrs.add(addrs);
							}
						}
						companyprofile.setCompanyAddress(listAddrs);
						companyProfileService.saveCompanyDetails(companyprofile);

						message.setMessageId("FOUND");
						message.setMessageDetail("Found!");
						response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.OK);
				}else {
					message.setMessageId("NOT_FOUND");
					message.setMessageDetail("Details Not Found!");
					return new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.NOT_FOUND);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Updating Data");
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Exception occured while Updating Data");
			response = new ResponseEntity<ResponseVO<CompanyProfileVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
}
