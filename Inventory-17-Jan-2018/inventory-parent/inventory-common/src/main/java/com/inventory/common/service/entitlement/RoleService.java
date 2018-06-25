package com.inventory.common.service.entitlement;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.inventory.common.modal.entitlement.Role;

public interface RoleService {
    Role addRole(String roleName);
	Role findByRoleId(Integer roleId);
	Role findByRoleName(String roleName);
	Role findByRoleNameAndEnable(String roleName, Boolean enable);
	void updateEnableFlag(Boolean enable, Integer roleId);
	List<Role> findByEnable(Boolean enable, PageRequest pageRequest);
	List<Role> findAll();
	void updateRole(Role role);
}
