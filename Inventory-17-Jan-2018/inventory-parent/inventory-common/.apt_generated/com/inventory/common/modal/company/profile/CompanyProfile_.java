package com.inventory.common.modal.company.profile;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompanyProfile.class)
public abstract class CompanyProfile_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<CompanyProfile, String> gstNumber;
	public static volatile SingularAttribute<CompanyProfile, Long> companyId;
	public static volatile SingularAttribute<CompanyProfile, String> website;
	public static volatile ListAttribute<CompanyProfile, CompanyBank> companyBank;
	public static volatile SingularAttribute<CompanyProfile, Long> phone;
	public static volatile SingularAttribute<CompanyProfile, String> companyName;
	public static volatile ListAttribute<CompanyProfile, CompanyAddress> companyAddress;
	public static volatile SingularAttribute<CompanyProfile, String> description;
	public static volatile SingularAttribute<CompanyProfile, String> email;

}

