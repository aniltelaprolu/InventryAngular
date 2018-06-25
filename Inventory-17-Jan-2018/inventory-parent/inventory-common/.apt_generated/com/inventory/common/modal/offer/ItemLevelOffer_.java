package com.inventory.common.modal.offer;

import com.inventory.common.constants.AppConstants.OFFERSTATUS;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.vendor.InvVendor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemLevelOffer.class)
public abstract class ItemLevelOffer_ extends com.inventory.common.modal.base.BaseAuditModel_ {

	public static volatile SingularAttribute<ItemLevelOffer, Long> offerDetailId;
	public static volatile SingularAttribute<ItemLevelOffer, String> offerName;
	public static volatile SingularAttribute<ItemLevelOffer, Integer> quantity;
	public static volatile SingularAttribute<ItemLevelOffer, Long> productId;
	public static volatile SingularAttribute<ItemLevelOffer, Date> endDate;
	public static volatile SingularAttribute<ItemLevelOffer, Double> flatDiscount;
	public static volatile SingularAttribute<ItemLevelOffer, String> description;
	public static volatile SingularAttribute<ItemLevelOffer, InvVendor> invVendor;
	public static volatile SingularAttribute<ItemLevelOffer, OFFERSTATUS> offerStatus;
	public static volatile SingularAttribute<ItemLevelOffer, Double> discountPercentage;
	public static volatile SingularAttribute<ItemLevelOffer, Long> offerCode;
	public static volatile SingularAttribute<ItemLevelOffer, Long> variantId;
	public static volatile SetAttribute<ItemLevelOffer, InventoryItem> items;
	public static volatile SingularAttribute<ItemLevelOffer, Date> startDate;

}

