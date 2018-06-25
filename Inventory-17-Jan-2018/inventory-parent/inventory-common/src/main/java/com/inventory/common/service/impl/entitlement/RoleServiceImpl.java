package com.inventory.common.service.impl.entitlement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inventory.common.constants.BaseDAOConstants;
import com.inventory.common.modal.entitlement.Role;
import com.inventory.common.repository.entitlement.RoleRepository;
import com.inventory.common.service.entitlement.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByRoleNameAndEnable(String roleName, Boolean enable) {
		logger.info("update role {} with enable flag as {}", roleName, enable);
		return roleRepository.findByRoleNameAndEnable(roleName, enable);
	}

	@Override
	public void updateEnableFlag(Boolean enable, Integer roleId) {
		logger.info("update role {} with enable flag value as {}", roleId, enable);
		roleRepository.updateEnableFlag(enable, roleId);
	}

	@Override
	public List<Role> findByEnable(Boolean enable, PageRequest pageRequest) {
		logger.info("find all roles with enable flag value as {}", enable);
		if(pageRequest == null) {
			pageRequest = new PageRequest(0, BaseDAOConstants.DEFAULT_PAGE_SIZE);
		}
		Page<Role> roles = roleRepository.findByEnable(enable, pageRequest);
		return roles.getContent();
	}

	@Override
	public Role findByRoleName(String roleName) {
		logger.info("find role by name {}", roleName);
		return roleRepository.findByRoleNameAndEnable(roleName, Boolean.TRUE);
	}

	@Override
	public List<Role> findAll() {
		logger.info("find all roles");
		return roleRepository.findAll();
	}

	@Override
	public Role findByRoleId(Integer roleId) {
		logger.info("find role by id {}", roleId);
		return roleRepository.findById(roleId);
	}

	@Override
	public Role addRole(String roleName) {
		logger.info("add role for role name {}", roleName);
		Role role = new Role();
		role.setEnable(Boolean.TRUE);
		role.setRoleName(roleName);
		return roleRepository.save(role);
	}

	@Override
	public void updateRole(Role role) {
		logger.info("updatin role for role name {}", role.getRoleName());
		roleRepository.save(role);
	}
	
}
