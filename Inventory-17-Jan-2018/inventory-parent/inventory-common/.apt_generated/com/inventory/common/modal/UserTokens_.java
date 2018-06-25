package com.inventory.common.modal;

import com.inventory.common.modal.entitlement.InvUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserTokens.class)
public abstract class UserTokens_ {

	public static volatile SingularAttribute<UserTokens, Long> expirationPeriod;
	public static volatile SingularAttribute<UserTokens, Long> id;
	public static volatile SingularAttribute<UserTokens, String> type;
	public static volatile SingularAttribute<UserTokens, String> tokenstr;
	public static volatile SingularAttribute<UserTokens, InvUser> user;
	public static volatile SingularAttribute<UserTokens, String> status;

}

