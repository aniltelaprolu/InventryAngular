package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.product.MenuGroup;

@Repository("menuGroupRepository")
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long>{
	
}
