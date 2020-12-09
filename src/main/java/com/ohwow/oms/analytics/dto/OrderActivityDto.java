package com.ohwow.oms.analytics.dto;

import com.ohwow.oms.order.OrderStatusEnum;

public class OrderActivityDto {

	private final OrderStatusEnum orderStatus;

	private final long number;

	public OrderActivityDto(OrderStatusEnum orderStatus, long number) {

		this.orderStatus = orderStatus;
		this.number = number;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public long getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (number ^ (number >>> 32));
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
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
		OrderActivityDto other = (OrderActivityDto) obj;
		if (number != other.number)
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		return true;
	}

}
