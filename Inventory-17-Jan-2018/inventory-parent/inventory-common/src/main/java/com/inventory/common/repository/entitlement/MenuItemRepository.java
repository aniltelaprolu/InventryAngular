package com.inventory.common.repository.entitlement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.MenuItem;


@Repository("menuItemRepository")
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	MenuItem findByMenuItemKeyAndEnable(String menuItemKey, Boolean enable);
	@Modifying
	@Query("update MenuItem mi set mi.enable = :enable where mi.menuItemKey = :menuItemKey")
	void updateEnableFlag(@Param(value="enable")Boolean enable, @Param(value="menuItemKey")String menuItemKey);
	@Query(value="select * from menu_item mi where mi.menu_id = :menuId AND mi.enable = :enable", nativeQuery = true)
	List<MenuItem> findByMenuAndEnable(@Param(value="menuId") Integer menuId, @Param(value="enable") Boolean enable);
}
