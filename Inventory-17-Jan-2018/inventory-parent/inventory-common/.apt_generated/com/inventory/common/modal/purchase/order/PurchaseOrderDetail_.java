package com.inventory.common.modal.purchase.order;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrderDetail.class)
public abstract class PurchaseOrderDetail_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<PurchaseOrderDetail, Long> purchaseOrderDetailId;
	public static volatile SingularAttribute<PurchaseOrderDetail, Product> product;
	public static volatile SingularAttribute<PurchaseOrderDetail, Integer> quantity;
	public static volatile SingularAttribute<PurchaseOrderDetail, Integer> leftQuantity;
	public static volatile SingularAttribute<PurchaseOrderDetail, Variant> variant;
	public static volatile SingularAttribute<PurchaseOrderDetail, PurchaseOrder> purchaseOrder;

}

