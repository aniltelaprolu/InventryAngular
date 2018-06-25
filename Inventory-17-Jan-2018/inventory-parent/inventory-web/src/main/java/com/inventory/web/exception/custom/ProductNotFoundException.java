package com.inventory.web.exception.custom;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3137549791927387696L;

	private String message;

	public ProductNotFoundException() {
		this("Product Is Not Available");
	}

	public ProductNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}

}
