package com.inventory.common.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.common.modal.product.VariantImage;

@Repository("imageRepository")
public interface VarinatImagesRepository extends JpaRepository<VariantImage, Long> {
	VariantImage findByImageId(Long imagesId);
}
