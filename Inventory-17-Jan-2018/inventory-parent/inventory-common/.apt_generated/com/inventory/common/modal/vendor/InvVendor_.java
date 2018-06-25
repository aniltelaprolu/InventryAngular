package com.inventory.common.modal.vendor;

import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvVendor.class)
public abstract class InvVendor_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile ListAttribute<InvVendor, VendorBankAccountDetails> vendorBankDetails;
	public static volatile ListAttribute<InvVendor, InventoryItem> inventoryItem;
	public static volatile SingularAttribute<InvVendor, String> venId;
	public static volatile ListAttribute<InvVendor, ReceivedOrder> reveivedOrder;
	public static volatile SingularAttribute<InvVendor, Long> venderId;
	public static volatile SingularAttribute<InvVendor, String> name;
	public static volatile ListAttribute<InvVendor, PurchaseOrder> purchaseOrder;
	public static volatile SetAttribute<InvVendor, VendorPhone> phones;
	public static volatile SingularAttribute<InvVendor, String> email;
	public static volatile ListAttribute<InvVendor, VendorAddress> venAddress;
	public static volatile SetAttribute<InvVendor, Product> products;

}

