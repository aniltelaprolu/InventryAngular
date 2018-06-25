package com.inventory.common.modal.product;

import com.inventory.common.constants.AppConstants.PRODUCTSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile ListAttribute<Product, InventoryItem> inventoryItem;
	public static volatile SingularAttribute<Product, SubCategory> subCategory;
	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile ListAttribute<Product, ReceivedOrderDetail> receivedOrderDetails;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, PRODUCTSTATUS> productStatus;
	public static volatile SetAttribute<Product, Variant> variants;
	public static volatile SingularAttribute<Product, MenuGroup> menuGroup;
	public static volatile SingularAttribute<Product, String> productName;
	public static volatile SingularAttribute<Product, String> productCode;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile SetAttribute<Product, InvVendor> vendors;
	public static volatile SingularAttribute<Product, STATUS> status;

}

