package com.inventory.common.modal.product;

import com.inventory.common.constants.AppConstants.STATUS;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VariantParameter.class)
public abstract class VariantParameter_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<VariantParameter, Long> variantParameterId;
	public static volatile SingularAttribute<VariantParameter, Parameter> parameter;
	public static volatile SingularAttribute<VariantParameter, Variant> variant;
	public static volatile SingularAttribute<VariantParameter, String> value;
	public static volatile SingularAttribute<VariantParameter, STATUS> status;

}

