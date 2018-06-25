package com.inventory.common.modal.entitlement;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenuItem.class)
public abstract class MenuItem_ {

	public static volatile SingularAttribute<MenuItem, Boolean> enable;
	public static volatile SingularAttribute<MenuItem, String> name;
	public static volatile SingularAttribute<MenuItem, String> menuItemKey;
	public static volatile SingularAttribute<MenuItem, Menu> menu;
	public static volatile SetAttribute<MenuItem, Action> actions;
	public static volatile SingularAttribute<MenuItem, Integer> menuItemId;
	public static volatile SingularAttribute<MenuItem, String> url;

}

