package com.inventory.common.constants;

public class AppConstants {
	
	/** The Constant BLANK. */
	public static final String BLANK = "";
	
	/** The Constant COLON. */
	public static final String COLON = " : ";
	
	/** The Constant DASH. */
	public static final String DASH = " - ";
	
	/** The Constant KEY_TXNID. */
	public static final String KEY_TXNID = "TXNID";

	//public static enum PRODUCTSTATUS {PARTIALLY_ACTIVE, ACTIVE, INACTIVE, BROADCASTED}
	
	public static enum BRANDSTATUS {ACTIVE, INACTIVE}
	
	public static enum STATUS {ACTIVE, INACTIVE}
	
	public static enum USERSTATUS {ACTIVE, INACTIVE, DELETED, DISABLED, LOCKED}
	
	public static enum PHONETYPE {OFFICE, HOME, PERSONAL}
	
	public static enum ADDRESSTYPE {OFFICE, HOME, SHIPPING}
	
	public static enum ITEMSTATUS {ACTIVE, INACTIVE}
	
	public static enum PRODUCTSTATUS {AVAILABLE,NOTAVAILABLE, BROADCASTED}
	
	public static enum POITEMSTATUS {PENDING,PARTIALLY,COMPLETED}
	
	public static enum POSTATUS	{PENDING,PARTIALLY,COMPLETED}
	
	public static enum ROSTATUS	{PARTIALLY,COMPLETED}
	
	public static enum OFFERSTATUS {ONOFFER,EXPIRED}
	
	public static enum INVENTORYPAYMENTSTATUS {COMPLETED, PARTIAL, PENDING}
	
	public static enum PAYBY {CHEQUE, BANKTRANSFER}
	
	public static Integer MAX_RETRY_COUNT = 3;

}
