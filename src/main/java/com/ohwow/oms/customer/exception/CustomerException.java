package com.ohwow.oms.customer.exception;

public class CustomerException extends Exception {

	public static final String INVALID_CUSTOMER_EXCEPTION = "Invalid Customer";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2926895512355420258L;

	public CustomerException(String message) {
		super(message);
	}
}
