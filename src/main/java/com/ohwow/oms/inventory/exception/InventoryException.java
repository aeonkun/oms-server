package com.ohwow.oms.inventory.exception;

public class InventoryException extends Exception {

	public static final String NO_EXISTING_INVENTORY_FOR_PRODUCT_EXCEPTION = "No existing inventory for product";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2436953922545898896L;

	public InventoryException(String message) {
		super(message);
	}

}
