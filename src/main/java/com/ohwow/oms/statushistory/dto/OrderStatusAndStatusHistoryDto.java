package com.ohwow.oms.statushistory.dto;

import java.util.List;

import com.ohwow.oms.order.OrderStatusEnum;

public class OrderStatusAndStatusHistoryDto {

	private OrderStatusEnum orderStatus;
	private List<StatusHistoryDto> statusAndHistories;

	public OrderStatusAndStatusHistoryDto(OrderStatusEnum orderStatus, List<StatusHistoryDto> statusAndHistories) {
		this.orderStatus = orderStatus;
		this.statusAndHistories = statusAndHistories;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<StatusHistoryDto> getStatusAndHistories() {
		return statusAndHistories;
	}

	public void setStatusAndHistories(List<StatusHistoryDto> statusAndHistories) {
		this.statusAndHistories = statusAndHistories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((statusAndHistories == null) ? 0 : statusAndHistories.hashCode());
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
		OrderStatusAndStatusHistoryDto other = (OrderStatusAndStatusHistoryDto) obj;
		if (orderStatus != other.orderStatus)
			return false;
		if (statusAndHistories == null) {
			if (other.statusAndHistories != null)
				return false;
		} else if (!statusAndHistories.equals(other.statusAndHistories))
			return false;
		return true;
	}

}
