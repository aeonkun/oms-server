package com.ohwow.oms.products.dto;

import java.time.LocalDateTime;

import com.ohwow.oms.order.OrderStatusEnum;

public class ProductTransactionDto {

	private final LocalDateTime dateTimeCreated;
	private final long orderId;
	private final String customerName;
	private final int quantity;
	private final long price;
	private final long total;
	private final OrderStatusEnum status;

	public ProductTransactionDto(LocalDateTime dateTimeCreated, long orderId, String firstName, String lastName,
			int quantity, long price, long total, OrderStatusEnum status) {
		this.dateTimeCreated = dateTimeCreated;
		this.orderId = orderId;
		this.customerName = firstName + " " + lastName;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.status = status;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public long getOrderId() {
		return orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getPrice() {
		return price;
	}

	public long getTotal() {
		return total;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public String getCustomerName() {
		return customerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dateTimeCreated == null) ? 0 : dateTimeCreated.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ProductTransactionDto other = (ProductTransactionDto) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateTimeCreated == null) {
			if (other.dateTimeCreated != null)
				return false;
		} else if (!dateTimeCreated.equals(other.dateTimeCreated))
			return false;
		if (orderId != other.orderId)
			return false;
		if (price != other.price)
			return false;
		if (quantity != other.quantity)
			return false;
		if (status != other.status)
			return false;
		if (total != other.total)
			return false;
		return true;
	}

}
