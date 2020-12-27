package com.ohwow.oms.statushistory.dto;

import java.time.LocalDateTime;

import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.statushistory.domain.StatusHistory;

public class StatusHistoryDto {

	private long orderId;
	private OrderStatusEnum oldStatus;
	private OrderStatusEnum newStatus;
	private String modifiedBy;
	private LocalDateTime dateTimeModified;

	public StatusHistoryDto(long orderId, OrderStatusEnum oldStatus, OrderStatusEnum newStatus, String modifiedBy,
			LocalDateTime dateTimeModified) {
		this.orderId = orderId;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
	}

	public StatusHistoryDto(StatusHistory statusHistory) {
		this.orderId = statusHistory.getOrder().getId();
		this.oldStatus = statusHistory.getOldStatus();
		this.newStatus = statusHistory.getNewStatus();
		this.modifiedBy = statusHistory.getModifiedBy();
		this.dateTimeModified = statusHistory.getDateTimeModified();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public OrderStatusEnum getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(OrderStatusEnum oldStatus) {
		this.oldStatus = oldStatus;
	}

	public OrderStatusEnum getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(OrderStatusEnum newStatus) {
		this.newStatus = newStatus;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getDateTimeModified() {
		return dateTimeModified;
	}

	public void setDateTimeModified(LocalDateTime dateTimeModified) {
		this.dateTimeModified = dateTimeModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTimeModified == null) ? 0 : dateTimeModified.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((newStatus == null) ? 0 : newStatus.hashCode());
		result = prime * result + ((oldStatus == null) ? 0 : oldStatus.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
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
		StatusHistoryDto other = (StatusHistoryDto) obj;
		if (dateTimeModified == null) {
			if (other.dateTimeModified != null)
				return false;
		} else if (!dateTimeModified.equals(other.dateTimeModified))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (newStatus != other.newStatus)
			return false;
		if (oldStatus != other.oldStatus)
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}

}
