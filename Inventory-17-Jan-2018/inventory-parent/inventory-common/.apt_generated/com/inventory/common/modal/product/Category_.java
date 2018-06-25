package com.inventory.common.modal.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<Category, String> categoryCode;
	public static volatile SingularAttribute<Category, MenuGroup> menuGroup;
	public static volatile SingularAttribute<Category, String> categoryName;
	public static volatile SingularAttribute<Category, Long> categoryId;
	public static volatile ListAttribute<Category, SubCategory> subCategories;

}

