package com.inventory.common.modal.entitlement;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Action.class)
public abstract class Action_ {

	public static volatile SingularAttribute<Action, Boolean> enable;
	public static volatile SingularAttribute<Action, String> actionKey;
	public static volatile SingularAttribute<Action, Integer> actionId;
	public static volatile SingularAttribute<Action, String> action;
	public static volatile SingularAttribute<Action, MenuItem> menuItem;
	public static volatile SingularAttribute<Action, String> url;

}

