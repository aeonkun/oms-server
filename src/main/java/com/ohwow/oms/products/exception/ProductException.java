package com.ohwow.oms.products.exception;

public class ProductException extends Exception {

	public static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product not found";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductException(String message) {
		super(message);
	}

}
