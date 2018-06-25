package com.inventory.common.modal.entitlement;

import com.inventory.common.constants.AppConstants.USERSTATUS;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvUser.class)
public abstract class InvUser_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<InvUser, String> lastName;
	public static volatile ListAttribute<InvUser, InvUserAddress> invUserAddress;
	public static volatile SingularAttribute<InvUser, String> password;
	public static volatile SingularAttribute<InvUser, Role> role;
	public static volatile SingularAttribute<InvUser, Integer> failedAttempts;
	public static volatile SingularAttribute<InvUser, String> name;
	public static volatile SingularAttribute<InvUser, Integer> isFirstTime;
	public static volatile SetAttribute<InvUser, InvUserPhone> invUserPhones;
	public static volatile SingularAttribute<InvUser, Long> id;
	public static volatile SingularAttribute<InvUser, Integer> locked;
	public static volatile SingularAttribute<InvUser, String> email;
	public static volatile SingularAttribute<InvUser, USERSTATUS> status;

}

