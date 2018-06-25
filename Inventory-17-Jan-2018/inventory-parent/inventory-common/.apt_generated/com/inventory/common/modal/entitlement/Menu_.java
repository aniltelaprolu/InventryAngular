package com.inventory.common.modal.entitlement;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

	public static volatile SetAttribute<Menu, Role> role;
	public static volatile SingularAttribute<Menu, Boolean> enable;
	public static volatile SingularAttribute<Menu, String> name;
	public static volatile SetAttribute<Menu, MenuItem> menuItems;
	public static volatile SingularAttribute<Menu, Integer> menuId;
	public static volatile SingularAttribute<Menu, String> type;
	public static volatile SingularAttribute<Menu, String> url;

}

