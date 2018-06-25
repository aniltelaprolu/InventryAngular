package com.inventory.common.modal.entitlement;

import com.inventory.common.constants.AppConstants.ADDRESSTYPE;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvUserAddress.class)
public abstract class InvUserAddress_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<InvUserAddress, String> pincode;
	public static volatile SingularAttribute<InvUserAddress, String> country;
	public static volatile SingularAttribute<InvUserAddress, String> city;
	public static volatile SingularAttribute<InvUserAddress, ADDRESSTYPE> addressType;
	public static volatile SingularAttribute<InvUserAddress, String> addressLine1;
	public static volatile SingularAttribute<InvUserAddress, String> addressLine2;
	public static volatile SingularAttribute<InvUserAddress, String> addressLine3;
	public static volatile SingularAttribute<InvUserAddress, String> state;
	public static volatile SingularAttribute<InvUserAddress, Long> addressId;

}

