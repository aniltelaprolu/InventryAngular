package com.inventory.common.repository.entitlement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.Role;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findById(Integer roleId);
	Role findByRoleNameAndEnable(String roleName, Boolean enable);
	@Modifying
	@Query("update Role r set r.enable = :enable where r.id = :roleId")
	void updateEnableFlag(@Param(value="enable")Boolean enable, @Param(value="roleId")Integer roleId);
	Page<Role> findByEnable(Boolean enable, Pageable pageRequest);
}
