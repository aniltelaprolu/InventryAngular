package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.product.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
