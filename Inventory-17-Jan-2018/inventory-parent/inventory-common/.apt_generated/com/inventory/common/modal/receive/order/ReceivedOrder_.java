package com.inventory.common.modal.receive.order;

import com.inventory.common.constants.AppConstants.ROSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReceivedOrder.class)
public abstract class ReceivedOrder_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<ReceivedOrder, ROSTATUS> roStatus;
	public static volatile SingularAttribute<ReceivedOrder, Date> shippingDate;
	public static volatile SingularAttribute<ReceivedOrder, InvVendor> invVendor;
	public static volatile SetAttribute<ReceivedOrder, PurchaseOrderDetail> purchaseOrderDetail;
	public static volatile SingularAttribute<ReceivedOrder, Long> receivedOrderId;
	public static volatile SingularAttribute<ReceivedOrder, Date> receivedOrderDate;
	public static volatile SingularAttribute<ReceivedOrder, String> receviedOrderNo;
	public static volatile SingularAttribute<ReceivedOrder, Double> dueAmount;
	public static volatile SetAttribute<ReceivedOrder, ReceivedOrderDetail> receivedOrderDetail;
	public static volatile SingularAttribute<ReceivedOrder, PurchaseOrder> purchaseOrder;
	public static volatile SingularAttribute<ReceivedOrder, String> shippingAddress;
	public static volatile SingularAttribute<ReceivedOrder, String> billingAddress;
	public static volatile SingularAttribute<ReceivedOrder, STATUS> status;

}

