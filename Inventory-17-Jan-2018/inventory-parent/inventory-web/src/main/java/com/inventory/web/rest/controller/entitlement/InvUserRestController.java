package com.inventory.web.rest.controller.entitlement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.ADDRESSTYPE;
import com.inventory.common.constants.AppConstants.PHONETYPE;
import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.modal.entitlement.InvUserAddress;
import com.inventory.common.modal.entitlement.InvUserPhone;
import com.inventory.common.modal.entitlement.Role;
import com.inventory.common.service.entitlement.InvUserService;
import com.inventory.common.service.entitlement.RoleService;
import com.inventory.common.util.PasswordGenrator;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchRequestVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.entitlement.InvUserAddressVO;
import com.inventory.web.rest.vo.entitlement.InvUserPhoneVO;
import com.inventory.web.rest.vo.entitlement.InvUserVO;
import com.inventory.common.service.EmailService;

@RestController
@RequestMapping("/service/user")
//@RequestMapping("/externalservice/user")
public class InvUserRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvUserRestController.class);
	
	@Autowired
	private InvUserService userService;

	@Autowired
	private PasswordGenrator passwordGenrator;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmailService ser;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseVO<InvUserVO>> addNewUser(@RequestBody InvUserVO UserObj) {
		ResponseVO<InvUserVO> message = new  ResponseVO<InvUserVO>();
		if(UserObj == null ) {
			message.setMessageId("BLANK_USER");
			message.setMessageDetail("User can not blank");
		}

		try {
			String requestedUser = UserObj.getUserEmail();
			InvUser invUser = userService.findUserByEmail(requestedUser);
			if(invUser != null) {
				message.setMessageId("USER_EXSIST");
				message.setMessageDetail("User already exsist in the system");
				return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.NOT_ACCEPTABLE);
			}

			InvUser user = new InvUser();
			user.setStatus(USERSTATUS.ACTIVE);
			user.setEmail(UserObj.getUserEmail());
			user.setName(UserObj.getFirstName());
			user.setLastName(UserObj.getLastName());
			String password = passwordGenrator.generatePassword();
			System.out.println("password : " + password);
			user.setPassword(password);
			userService.saveUser(user);
			
			//MailUtil.sendMail(password);
			ser.sendNewUserRegistrationMail(invUser);
			
			message.setMessageId("USER_CREATED");
			message.setMessageDetail("User created sucessfully");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {

		}

		return null;
	}

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<InvUserVO>> deleteUser(@PathVariable Long userId) {
		ResponseVO<InvUserVO> message = new  ResponseVO<InvUserVO>();

		if(userId == 0 ) {
			message.setMessageId("BLANCK_ID");
			message.setMessageDetail("User id can not blank");
		}
		try {
			userService.updateStatus(USERSTATUS.DELETED, userId);
			
			message.setMessageId("USER_DELETED");
			message.setMessageDetail("User created sucessfully");
		}catch(Exception e) {
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error while deleting user");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<InvUserVO>> updateUser(@RequestBody InvUserVO userObj) {
		ResponseVO<InvUserVO> message = new  ResponseVO<InvUserVO>();

		if(userObj == null ) {
			message.setMessageId("BLANCK_USER");
			message.setMessageDetail("User can not blank");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.METHOD_NOT_ALLOWED);
		}

		InvUser user = userService.findUserById(userObj.getUserId());
		if(user == null) {
			message.setMessageId("INVALID_USER");
			message.setMessageDetail("Requested User is not valid");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Role role = roleService.findByRoleName(userObj.getRole());
		if(role == null) {
			message.setMessageId("INVALID_ROLE_SELECTED");
			message.setMessageDetail("Role is not found");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.NOT_ACCEPTABLE);
		}
		try {

			user.setName(userObj.getFirstName());
			user.setLastName(userObj.getLastName());
			user.setRole(role);
			user.setStatus(USERSTATUS.valueOf(userObj.getStatus()));
			
			List<InvUserAddressVO> listAddressVO = userObj.getInvUserAddresses();
			InvUserAddress userAddress = null;
			List<InvUserAddress> listAddrs = new ArrayList<>();
			for(InvUserAddressVO addrsVO : listAddressVO) {
				userAddress = new InvUserAddress();
				userAddress.setAddressId(addrsVO.getAddressId());
				userAddress.setAddressLine1(addrsVO.getAddressLine1());
				userAddress.setAddressLine2(addrsVO.getAddressLine2());
				userAddress.setAddressLine3(addrsVO.getAddressLine3());
				userAddress.setAddressType(ADDRESSTYPE.valueOf(addrsVO.getAddressType()));
				userAddress.setCity(addrsVO.getCity());
				userAddress.setCountry(addrsVO.getCountry());
				userAddress.setPincode(addrsVO.getPincode());
				userAddress.setState(addrsVO.getState());
				listAddrs.add(userAddress);
			}
			
			Set<InvUserPhoneVO> listPhoneVO = userObj.getInvUserPhones();
			InvUserPhone phone = null;
			Set<InvUserPhone> listPhone = new HashSet<InvUserPhone>();
			for(InvUserPhoneVO phoneVO: listPhoneVO) {
				phone = new InvUserPhone();
				phone.setVendorPhoneId(phoneVO.getVendorPhoneId());
				phone.setPhoneType(PHONETYPE.valueOf(phoneVO.getPhoneType()));
				phone.setPhonNo(phoneVO.getPhonNo());
				listPhone.add(phone);
			}
			
			user.setInvUserAddress(listAddrs);
			user.setInvUserPhones(listPhone);
			userService.updateUser(user);
			message.setMessageId("USER_UPDATE");
			message.setMessageDetail("User updated sucessfully");
		}catch(Exception e) {
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Error while updating user");
			return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<ResponseVO<InvUserVO>>(message, HttpStatus.OK);
	}

	@RequestMapping(value="/getAll", method=RequestMethod.POST)
	public ResponseEntity<SearchResponseVO<InvUserVO>> getAllInvUsers(@RequestBody SearchRequestVO searchRequestVO) {
		SearchResponseVO<InvUserVO> message = new  SearchResponseVO<InvUserVO>();
		try {
			Integer pageNo = searchRequestVO.getPageNo();
			Integer pageSize = searchRequestVO.getPageSize();
			
			PageRequest pageRequest = new PageRequest(pageNo-1, pageSize);
			List<InvUser> invUsers = userService.findAll(pageRequest);
			
			List<InvUserVO> invUserVOs = new ArrayList<InvUserVO>();
			InvUserVO invUserVO = null;
			for(InvUser invUser : invUsers) {
				invUserVO = new InvUserVO();
				invUserVO.setFirstName(invUser.getName());
				invUserVO.setLastName(invUser.getLastName());
				invUserVO.setRole(invUser.getRole().getRoleName());
				invUserVO.setStatus(invUser.getStatus().toString());
				invUserVO.setUserEmail(invUser.getEmail());
				invUserVO.setUserId(invUser.getId());
				invUserVOs.add(invUserVO);
			}
			
			message.setMessageId("GET_ALL_INV_USERS");
			message.setMessageDetail("get all inv users");
			message.setData(invUserVOs);
			return new ResponseEntity<SearchResponseVO<InvUserVO>>(message, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Error occured while performing get all inv users operation", e);
			message.setMessageId("GET_ALL_INV_USERS_FAILED");
			message.setMessageDetail("Error occured while performing get all inv users operation");
			return new ResponseEntity<SearchResponseVO<InvUserVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
