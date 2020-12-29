package com.ohwow.oms.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ohwow.oms.customer.dto.CustomerDto;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.orderdetails.dto.OrderDetailDto;
import com.ohwow.oms.statushistory.dto.StatusHistoryDto;

public class OrderDto {

	private final long id;
	private final CustomerDto customer;
	private final String createdBy;
	private final LocalDateTime dateTimeCreated;
	private final String modifiedBy;
	private final LocalDateTime dateTimeModified;
	private final long totalPrice;
	private final OrderStatusEnum orderStatus;
	private final List<OrderDetailDto> orderDetails;
	private final List<StatusHistoryDto> statusHistories;
	private final String paymentMethod;
	private final String additionalNotes;
	private final boolean hasStockIssues;
	private final String municipality;
	private final long deliveryCharge;

	public OrderDto(long id, CustomerDto customer, String createdBy, LocalDateTime dateTimeCreated, String modifiedBy,
			LocalDateTime dateTimeModified, long totalPrice, OrderStatusEnum orderStatus,
			List<OrderDetailDto> orderDetails, List<StatusHistoryDto> statusHistories, String paymentMethod,
			String additionalNotes, boolean hasStockIssues, String municipality, long deliveryCharge) {
		this.id = id;
		this.customer = customer;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
		this.statusHistories = statusHistories;
		this.paymentMethod = paymentMethod;
		this.additionalNotes = additionalNotes;
		this.hasStockIssues = hasStockIssues;
		this.municipality = municipality;
		this.deliveryCharge = deliveryCharge;
	}

	public long getId() {
		return id;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public LocalDateTime getDateTimeModified() {
		return dateTimeModified;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public List<OrderDetailDto> getOrderDetails() {
		return orderDetails;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public List<StatusHistoryDto> getStatusHistories() {
		return statusHistories;
	}

	public boolean isHasStockIssues() {
		return hasStockIssues;
	}

	public String getMunicipality() {
		return municipality;
	}

	public long getDeliveryCharge() {
		return deliveryCharge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalNotes == null) ? 0 : additionalNotes.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dateTimeCreated == null) ? 0 : dateTimeCreated.hashCode());
		result = prime * result + ((dateTimeModified == null) ? 0 : dateTimeModified.hashCode());
		result = prime * result + (int) (deliveryCharge ^ (deliveryCharge >>> 32));
		result = prime * result + (hasStockIssues ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((municipality == null) ? 0 : municipality.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		result = prime * result + ((statusHistories == null) ? 0 : statusHistories.hashCode());
		result = prime * result + (int) (totalPrice ^ (totalPrice >>> 32));
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
		OrderDto other = (OrderDto) obj;
		if (additionalNotes == null) {
			if (other.additionalNotes != null)
				return false;
		} else if (!additionalNotes.equals(other.additionalNotes))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dateTimeCreated == null) {
			if (other.dateTimeCreated != null)
				return false;
		} else if (!dateTimeCreated.equals(other.dateTimeCreated))
			return false;
		if (dateTimeModified == null) {
			if (other.dateTimeModified != null)
				return false;
		} else if (!dateTimeModified.equals(other.dateTimeModified))
			return false;
		if (deliveryCharge != other.deliveryCharge)
			return false;
		if (hasStockIssues != other.hasStockIssues)
			return false;
		if (id != other.id)
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (municipality == null) {
			if (other.municipality != null)
				return false;
		} else if (!municipality.equals(other.municipality))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		if (statusHistories == null) {
			if (other.statusHistories != null)
				return false;
		} else if (!statusHistories.equals(other.statusHistories))
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}

}
