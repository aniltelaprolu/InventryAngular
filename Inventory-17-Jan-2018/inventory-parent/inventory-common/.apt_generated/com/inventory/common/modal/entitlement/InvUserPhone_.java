package com.inventory.common.modal.entitlement;

import com.inventory.common.constants.AppConstants.PHONETYPE;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvUserPhone.class)
public abstract class InvUserPhone_ {

	public static volatile SingularAttribute<InvUserPhone, PHONETYPE> phoneType;
	public static volatile SingularAttribute<InvUserPhone, Long> phonNo;
	public static volatile SingularAttribute<InvUserPhone, Long> vendorPhoneId;

}

