package com.inventory.common.modal.entitlement;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<Role, Boolean> enable;
	public static volatile SingularAttribute<Role, String> roleName;
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SetAttribute<Role, Menu> menus;
	public static volatile ListAttribute<Role, InvUser> invUsers;

}

