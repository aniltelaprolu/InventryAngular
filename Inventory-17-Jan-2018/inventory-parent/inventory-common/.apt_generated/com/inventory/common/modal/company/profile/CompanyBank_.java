package com.inventory.common.modal.company.profile;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompanyBank.class)
public abstract class CompanyBank_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<CompanyBank, String> bankName;
	public static volatile SingularAttribute<CompanyBank, Long> companyBankId;
	public static volatile SingularAttribute<CompanyBank, Long> accountNumber;
	public static volatile SingularAttribute<CompanyBank, String> accountHolderName;
	public static volatile SingularAttribute<CompanyBank, String> branch;
	public static volatile SingularAttribute<CompanyBank, String> ifscCode;

}

