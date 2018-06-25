package com.inventory.common.modal.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VariantImage.class)
public abstract class VariantImage_ {

	public static volatile SingularAttribute<VariantImage, Long> imageId;
	public static volatile SingularAttribute<VariantImage, String> imageName;
	public static volatile SingularAttribute<VariantImage, String> imagePath;
	public static volatile SingularAttribute<VariantImage, Variant> variant;

}

