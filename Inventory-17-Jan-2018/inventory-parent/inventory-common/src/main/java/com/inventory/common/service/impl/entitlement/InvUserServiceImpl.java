package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.constants.AppConstants;
import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.entitlement.InvUser;
import com.inventory.common.modal.entitlement.Role;
import com.inventory.common.repository.entitlement.InvUserRepository;
import com.inventory.common.service.EmailService;
import com.inventory.common.service.entitlement.InvUserService;

@Service("invUserService")
public class InvUserServiceImpl implements InvUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(InvUserDetailsServiceImpl.class);
	
	@Autowired
	private InvUserRepository invUserRepository;
	
	@Autowired
    private RoleServiceImpl roleService;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private EmailService emailService;
    
	@Override
	public InvUser findUserByEmail(String email) {
		return invUserRepository.findByEmail(email);
	}
	
	@Override
	public void saveUser(InvUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(USERSTATUS.ACTIVE);
        user.setIsFirstTime(1);
        Role userRole = roleService.findByRoleName("ADMIN");
        user.setRole(userRole);
		invUserRepository.save(user);
		emailService.sendNewUserRegistrationMail(user);
	}

	@Override
	public Page<InvUser> getAllInvUsers(PageRequest pageRequest) {
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		return invUserRepository.findAll(pageRequest);
	}

	@Override
	public InvUser findUserById(Long id) {
		return invUserRepository.findById(id);
	}

	@Override
	public void deleteUser(Long userId) {
		invUserRepository.deleteById(userId);
	}
	
	@Override
	@Transactional
	public void updateUser(InvUser user) {
		InvUser invUser = invUserRepository.findByEmail(user.getEmail());
		if(invUser == null) {
			logger.error("user with email id : " + user.getEmail() + " not found to update");
			return;
		}
		
		user.setId(invUser.getId());
		invUserRepository.save(user);
	}

	@Override
	@Transactional
	public void resetFailedAttempt(String email) {
		InvUser invUser = findUserByEmail(email);
		if(invUser == null)
			return;
		
		invUser.setFailedAttempts(0);
		invUser.setLocked(0);
		invUserRepository.save(invUser);
	}
	
	@Override
	@Transactional
	public void updateFailedAttempt(String email) {
		InvUser invUser = findUserByEmail(email);
		if(invUser == null)
			return;
		
		Integer currentCount = invUser.getFailedAttempts();
		currentCount = currentCount == null ? 0 : currentCount;
		if((currentCount + 1) >= AppConstants.MAX_RETRY_COUNT) {
			invUser.setLocked(1);
			invUser.setFailedAttempts(AppConstants.MAX_RETRY_COUNT);
		}else {
			invUser.setFailedAttempts(currentCount + 1);
		}
		
		invUserRepository.save(invUser);
	}
	

	@Override
	public List<InvUser> findAll(PageRequest pageRequest) {
		logger.info("find all inv users ");
		
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<InvUser> invUserPage = invUserRepository.findAll(pageRequest);
		return invUserPage.getContent();
	}

	@Override
	@Transactional
	public void updateStatus(USERSTATUS userStatus, Long userId) {
		logger.info("update user id {} status with status {}", userId, userStatus.toString());
		invUserRepository.updateStatus(userStatus, userId);
	}

}
