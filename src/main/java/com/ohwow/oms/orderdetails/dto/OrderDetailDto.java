package com.ohwow.oms.orderdetails.dto;

import com.ohwow.oms.orderdetails.domain.OrderDetail;

public class OrderDetailDto {

	private final long productId;
	private final String productName;
	private final int quantity;
	private final long price;
	private final long total;
	private final boolean hasEnoughStock;

	public OrderDetailDto(long productId, String productName, int quantity, long price, long total,
			boolean hasEnoughStock) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.hasEnoughStock = hasEnoughStock;

	}

	public OrderDetailDto(OrderDetail orderDetail) {
		this.productId = orderDetail.getId();
		this.productName = orderDetail.getProduct().getItemName();
		this.quantity = orderDetail.getQuantity();
		this.price = orderDetail.getProduct().getPrice();
		this.total = orderDetail.getTotal();
		this.hasEnoughStock = orderDetail.hasEnoughStock();

	}

	public long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getTotal() {
		return total;
	}

	public long getPrice() {
		return price;
	}

	public boolean getHasEnoughStock() {
		return hasEnoughStock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasEnoughStock ? 1231 : 1237);
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
		result = prime * result + (int) (total ^ (total >>> 32));
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
		OrderDetailDto other = (OrderDetailDto) obj;
		if (hasEnoughStock != other.hasEnoughStock)
			return false;
		if (price != other.price)
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		if (total != other.total)
			return false;
		return true;
	}

}
