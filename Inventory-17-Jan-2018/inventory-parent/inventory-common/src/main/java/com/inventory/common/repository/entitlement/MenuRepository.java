package com.inventory.common.repository.entitlement;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.entitlement.Menu;


@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Integer>{
	Menu findByName(String menuName);
	Menu findByMenuId(Integer menuId);
	@Query(value="select m from Menu m where m.menuId in(:menuIds)")
	Set<Menu> findByMenuIds(@Param(value="menuIds") List<Integer> menuIds);
}
