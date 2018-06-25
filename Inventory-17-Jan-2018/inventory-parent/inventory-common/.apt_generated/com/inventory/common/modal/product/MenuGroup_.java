package com.inventory.common.modal.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenuGroup.class)
public abstract class MenuGroup_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<MenuGroup, String> menuGroupCode;
	public static volatile SingularAttribute<MenuGroup, Long> menuGroupId;
	public static volatile SingularAttribute<MenuGroup, String> menuGroupName;
	public static volatile ListAttribute<MenuGroup, Category> categories;

}

