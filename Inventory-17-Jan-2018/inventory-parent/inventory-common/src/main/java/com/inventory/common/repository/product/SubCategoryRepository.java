package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.product.SubCategory;

@Repository("subCategoryRepository")
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
