package com.inventory.common.modal.receive.order;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReceivedOrderDetail.class)
public abstract class ReceivedOrderDetail_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<ReceivedOrderDetail, Long> receivedOrderId;
	public static volatile SingularAttribute<ReceivedOrderDetail, Product> product;
	public static volatile SingularAttribute<ReceivedOrderDetail, Integer> quantity;
	public static volatile SingularAttribute<ReceivedOrderDetail, ReceivedOrder> receivedOrder;
	public static volatile SingularAttribute<ReceivedOrderDetail, Double> price;
	public static volatile SingularAttribute<ReceivedOrderDetail, Variant> variant;

}

