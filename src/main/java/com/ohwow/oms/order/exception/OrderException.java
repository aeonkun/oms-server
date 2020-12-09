package com.ohwow.oms.order.exception;

public class OrderException extends Exception {

	public static final String ORDER_NOT_FOUND_EXCEPTION = "Order not found";
	public static final String INVALID_ORDER_PARAMETER_EXCEPTION = "Invalid order parameters";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1709517458444650471L;

	public OrderException(String message) {
		super(message);
	}

}
