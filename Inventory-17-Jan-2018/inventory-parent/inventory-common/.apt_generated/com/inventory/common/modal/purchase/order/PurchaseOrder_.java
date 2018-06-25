package com.inventory.common.modal.purchase.order;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.vendor.InvVendor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrder.class)
public abstract class PurchaseOrder_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<PurchaseOrder, String> purchaseOrderNo;
	public static volatile SingularAttribute<PurchaseOrder, Long> purchaseOrderId;
	public static volatile ListAttribute<PurchaseOrder, ReceivedOrder> reveivedOrder;
	public static volatile SingularAttribute<PurchaseOrder, Date> requiredByDate;
	public static volatile SingularAttribute<PurchaseOrder, String> shippingAddress;
	public static volatile SingularAttribute<PurchaseOrder, String> billingAddress;
	public static volatile SingularAttribute<PurchaseOrder, POSTATUS> poStatus;
	public static volatile SingularAttribute<PurchaseOrder, InvVendor> invVendor;
	public static volatile SetAttribute<PurchaseOrder, PurchaseOrderDetail> purchaseOrderDetail;
	public static volatile SingularAttribute<PurchaseOrder, Date> orderDate;
	public static volatile SingularAttribute<PurchaseOrder, STATUS> status;

}

