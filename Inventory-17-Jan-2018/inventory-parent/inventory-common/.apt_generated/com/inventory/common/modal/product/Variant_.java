package com.inventory.common.modal.product;

import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Variant.class)
public abstract class Variant_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile ListAttribute<Variant, VariantImage> variantImages;
	public static volatile SingularAttribute<Variant, Integer> quantity;
	public static volatile ListAttribute<Variant, ReceivedOrderDetail> receivedOrderDetails;
	public static volatile SingularAttribute<Variant, Double> price;
	public static volatile SingularAttribute<Variant, Long> variantId;
	public static volatile SingularAttribute<Variant, String> variantName;
	public static volatile ListAttribute<Variant, VariantParameter> variantParameters;
	public static volatile SingularAttribute<Variant, STATUS> status;
	public static volatile SingularAttribute<Variant, Product> products;

}

