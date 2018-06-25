package com.inventory.common.modal.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parameter.class)
public abstract class Parameter_ {

	public static volatile SingularAttribute<Parameter, String> parameterOptions;
	public static volatile SingularAttribute<Parameter, Long> parameterId;
	public static volatile SingularAttribute<Parameter, String> parameterName;
	public static volatile ListAttribute<Parameter, VariantParameter> variantParameters;

}

