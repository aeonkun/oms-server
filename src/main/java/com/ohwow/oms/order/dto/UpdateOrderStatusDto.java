package com.ohwow.oms.order.dto;

import com.ohwow.oms.order.OrderStatusEnum;

public class UpdateOrderStatusDto {

	private final OrderStatusEnum orderStatus;
	private final String username;

	public UpdateOrderStatusDto(OrderStatusEnum orderStatus, String username) {
		this.orderStatus = orderStatus;
		this.username = username;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UpdateOrderStatusDto other = (UpdateOrderStatusDto) obj;
		if (orderStatus != other.orderStatus)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
