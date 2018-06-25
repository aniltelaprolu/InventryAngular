package com.inventory.common.modal.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubCategory.class)
public abstract class SubCategory_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<SubCategory, String> subCategoryCode;
	public static volatile SingularAttribute<SubCategory, Long> subCategoryId;
	public static volatile SingularAttribute<SubCategory, Category> category;
	public static volatile SingularAttribute<SubCategory, String> subCategoryName;

}

