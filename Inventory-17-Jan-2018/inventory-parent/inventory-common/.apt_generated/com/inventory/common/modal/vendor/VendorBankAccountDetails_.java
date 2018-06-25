package com.inventory.common.modal.vendor;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VendorBankAccountDetails.class)
public abstract class VendorBankAccountDetails_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<VendorBankAccountDetails, String> bankName;
	public static volatile SingularAttribute<VendorBankAccountDetails, Long> accountNumber;
	public static volatile SingularAttribute<VendorBankAccountDetails, String> accountHolderName;
	public static volatile SingularAttribute<VendorBankAccountDetails, String> branch;
	public static volatile SingularAttribute<VendorBankAccountDetails, String> ifscCode;
	public static volatile SingularAttribute<VendorBankAccountDetails, Long> vendorAccountId;

}

