package com.inventory.common.modal.receive.order;

import com.inventory.common.constants.AppConstants.POITEMSTATUS;
import com.inventory.common.modal.offer.ItemLevelOffer;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.vendor.InvVendor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventoryItem.class)
public abstract class InventoryItem_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<InventoryItem, Long> inventoryItemId;
	public static volatile SingularAttribute<InventoryItem, Date> purchaseDate;
	public static volatile SingularAttribute<InventoryItem, Double> sellingPrice;
	public static volatile SingularAttribute<InventoryItem, Product> product;
	public static volatile SingularAttribute<InventoryItem, Double> offerPrice;
	public static volatile SingularAttribute<InventoryItem, ReceivedOrder> receivedOrder;
	public static volatile SingularAttribute<InventoryItem, ItemLevelOffer> itemLevelOffer;
	public static volatile SingularAttribute<InventoryItem, POITEMSTATUS> inventoryItemStatus;
	public static volatile SingularAttribute<InventoryItem, Variant> variant;
	public static volatile SingularAttribute<InventoryItem, String> inventoryItemNo;
	public static volatile SingularAttribute<InventoryItem, Double> purchasePrice;
	public static volatile SingularAttribute<InventoryItem, InvVendor> invVendor;

}

