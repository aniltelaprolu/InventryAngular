package com.inventory.common.service.impl.entitlement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.service.entitlement.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	//@Autowired
	//private UserRoleRepository userRoleRepository;

	@Override
	@Transactional
	public void deleteUserRoleByUserId(Long userId) {
		//userRoleRepository.deleteByuserId(userId);
	}
}
