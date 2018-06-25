package com.inventory.common.repository.entitlement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.Action;


@Repository("actionRepository")
public interface ActionRepository extends JpaRepository<Action, Integer>{
	Action findByActionKeyAndEnable(String actionKey, Boolean enable);
	@Modifying
	@Query("update Action a set a.enable = ?1 where a.actionKey = ?2")
	void updateEnableFlag(Boolean enable, String actionKey);
	Page<Action> findByEnable(Boolean enable, Pageable pageRequest);
}
