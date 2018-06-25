package com.inventory.common.modal.company.profile;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompanyAddress.class)
public abstract class CompanyAddress_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<CompanyAddress, String> pincode;
	public static volatile SingularAttribute<CompanyAddress, String> country;
	public static volatile SingularAttribute<CompanyAddress, String> address;
	public static volatile SingularAttribute<CompanyAddress, String> city;
	public static volatile SingularAttribute<CompanyAddress, String> addressType;
	public static volatile SingularAttribute<CompanyAddress, String> state;
	public static volatile SingularAttribute<CompanyAddress, Long> addressId;

}

