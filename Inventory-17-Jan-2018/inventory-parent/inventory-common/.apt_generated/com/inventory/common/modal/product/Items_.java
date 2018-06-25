package com.inventory.common.modal.product;

import com.inventory.common.constants.AppConstants.ITEMSTATUS;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Items.class)
public abstract class Items_ {

	public static volatile SingularAttribute<Items, Long> itemId;
	public static volatile SingularAttribute<Items, ITEMSTATUS> status;

}

