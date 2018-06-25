package com.inventory.common.repository.entitlement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.constants.AppConstants.USERSTATUS;
import com.inventory.common.modal.entitlement.InvUser;


@Repository("userRepository")
public interface InvUserRepository extends JpaRepository<InvUser, Long> {
	 InvUser findByEmail(String email);
	 InvUser findById(Long userId);
	 void deleteById(Long userId);
	 @Modifying
	 @Query("update InvUser u set u.status = :status where u.id = :userId")
	 void updateStatus(@Param(value="status")USERSTATUS userStatus, @Param(value="userId")Long userId);
}
