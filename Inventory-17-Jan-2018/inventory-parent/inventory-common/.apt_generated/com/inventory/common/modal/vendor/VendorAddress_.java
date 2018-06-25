package com.inventory.common.modal.vendor;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VendorAddress.class)
public abstract class VendorAddress_ {

	public static volatile SingularAttribute<VendorAddress, String> pincode;
	public static volatile SingularAttribute<VendorAddress, String> country;
	public static volatile SingularAttribute<VendorAddress, String> address;
	public static volatile SingularAttribute<VendorAddress, String> city;
	public static volatile SingularAttribute<VendorAddress, String> addressType;
	public static volatile SingularAttribute<VendorAddress, String> state;
	public static volatile SingularAttribute<VendorAddress, Long> addressId;

}

