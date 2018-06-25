package com.inventory.common.service.entitlement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.modal.entitlement.InvUser;

public interface InvUserService {
	public InvUser findUserByEmail(String email);
	public void saveUser(InvUser user);
	public Page<InvUser> getAllInvUsers(PageRequest pageRequest);
	public InvUser findUserById(Long id);
	public void deleteUser(Long userId);
	public void updateUser(InvUser user);
	public void resetFailedAttempt(String email);
	public void updateFailedAttempt(String email);
	public List<InvUser> findAll(PageRequest pageRequest);
	public void updateStatus(USERSTATUS userStatus, Long userId);
}
