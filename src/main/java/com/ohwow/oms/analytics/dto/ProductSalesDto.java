package com.ohwow.oms.analytics.dto;

public class ProductSalesDto {
	private final String productName;
	private final int amountSold;

	public ProductSalesDto(String productName, int amountSold) {
		this.productName = productName;
		this.amountSold = amountSold;
	}

	public String getProductName() {
		return productName;
	}

	public int getAmountSold() {
		return amountSold;
	}

}
