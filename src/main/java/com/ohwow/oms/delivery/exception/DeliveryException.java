package com.ohwow.oms.delivery.exception;

public class DeliveryException extends Exception {

	public static final String ERROR_OCCURED_WHILE_SAVING_DELIVERY_DETAILS = "An error occured while saving delivery destination and charge";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2287593550939906046L;

	public DeliveryException(String message) {
		super(message);
	}
}
