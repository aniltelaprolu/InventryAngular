package com.inventory.common.modal.entitlement;

import com.inventory.common.modal.entitlement.compositekey.RoleMenuID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleMenuItem.class)
public abstract class RoleMenuItem_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<RoleMenuItem, RoleMenuID> roleMenuId;
	public static volatile SingularAttribute<RoleMenuItem, MenuItem> menuItem;

}

