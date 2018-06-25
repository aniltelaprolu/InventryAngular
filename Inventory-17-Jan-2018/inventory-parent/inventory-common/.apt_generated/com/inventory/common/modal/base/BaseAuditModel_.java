package com.inventory.common.modal.base;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseAuditModel.class)
public abstract class BaseAuditModel_ {

	public static volatile SingularAttribute<BaseAuditModel, Date> createdAt;
	public static volatile SingularAttribute<BaseAuditModel, String> updatedBy;
	public static volatile SingularAttribute<BaseAuditModel, String> createdBy;
	public static volatile SingularAttribute<BaseAuditModel, Date> updatedAt;

}

