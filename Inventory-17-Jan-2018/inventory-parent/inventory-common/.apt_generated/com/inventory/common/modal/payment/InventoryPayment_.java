package com.inventory.common.modal.payment;

import com.inventory.common.constants.AppConstants.INVENTORYPAYMENTSTATUS;
import com.inventory.common.constants.AppConstants.PAYBY;
import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorBankAccountDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventoryPayment.class)
public abstract class InventoryPayment_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<InventoryPayment, Double> totalAmount;
	public static volatile SingularAttribute<InventoryPayment, CompanyBank> companyBank;
	public static volatile SingularAttribute<InventoryPayment, Double> payAmount;
	public static volatile SingularAttribute<InventoryPayment, ReceivedOrder> receivedOrder;
	public static volatile SingularAttribute<InventoryPayment, Long> chequeNo;
	public static volatile SingularAttribute<InventoryPayment, INVENTORYPAYMENTSTATUS> inventoryPaymentStatus;
	public static volatile SingularAttribute<InventoryPayment, PAYBY> payBy;
	public static volatile SingularAttribute<InventoryPayment, VendorBankAccountDetails> vendorBankAccountDetails;
	public static volatile SingularAttribute<InventoryPayment, PurchaseOrder> purchaseOrder;
	public static volatile SingularAttribute<InventoryPayment, POSTATUS> poStatus;
	public static volatile SingularAttribute<InventoryPayment, InvVendor> invVendor;
	public static volatile SingularAttribute<InventoryPayment, Long> inventoryPaymentId;

}

