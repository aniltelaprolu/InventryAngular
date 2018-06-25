package com.inventory.modal.reports;

import com.inventory.common.constants.AppConstants.STATUS;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductStats.class)
public abstract class ProductStats_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<ProductStats, Long> totalNoOfProducts;
	public static volatile SingularAttribute<ProductStats, Long> productStatId;
	public static volatile SingularAttribute<ProductStats, Long> totalNoOfBroadcastedProducts;
	public static volatile SingularAttribute<ProductStats, Long> totalNoOfUnAvailableProducts;
	public static volatile SingularAttribute<ProductStats, Long> totalNoOfActiveProducts;
	public static volatile SingularAttribute<ProductStats, Long> totalNoOfInActiveProducts;
	public static volatile SingularAttribute<ProductStats, Long> totalNoOfAvailableProducts;
	public static volatile SingularAttribute<ProductStats, STATUS> status;

}

