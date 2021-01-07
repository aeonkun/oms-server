package com.ohwow.oms.analytics.dto;

public class ProductSalesDto {

	private final String productName;
	private final int amountSold;

	public ProductSalesDto(String productName, int amountSold) {
		this.productName = productName;
		this.amountSold = amountSold;
	}

	public ProductSalesDto(ProductSalesProjection productSalesProjection) {
		this.productName = productSalesProjection.getProductName();
		this.amountSold = productSalesProjection.getAmountSold();
	}

	public String getProductName() {
		return productName;
	}

	public int getAmountSold() {
		return amountSold;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountSold;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSalesDto other = (ProductSalesDto) obj;
		if (amountSold != other.amountSold)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}
