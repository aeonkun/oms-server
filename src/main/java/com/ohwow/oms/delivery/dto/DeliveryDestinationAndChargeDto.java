package com.ohwow.oms.delivery.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohwow.oms.delivery.domain.DeliveryDestinationAndCharge;

public class DeliveryDestinationAndChargeDto {

	private final String destination;
	private final long deliveryCharge;
	private final String createdBy;
	private final LocalDateTime datetimeCreated;
	private final boolean active;
	private final String lastModifiedBy;
	private final LocalDateTime dateTimeModified;

	@JsonCreator
	public DeliveryDestinationAndChargeDto(@JsonProperty("destination") String destination,
			@JsonProperty("deliveryCharge") long deliveryCharge, @JsonProperty("createdBy") String createdBy,
			@JsonProperty("lastModifiedBy") String lastModifiedBy) {
		this.destination = destination;
		this.deliveryCharge = deliveryCharge;
		this.createdBy = createdBy;
		this.datetimeCreated = null;
		this.active = true;
		this.lastModifiedBy = lastModifiedBy;
		this.dateTimeModified = null;
	}

	public DeliveryDestinationAndChargeDto(DeliveryDestinationAndCharge deliveryCharge) {
		this.destination = deliveryCharge.getDestination();
		this.deliveryCharge = deliveryCharge.getDeliveryCharge();
		this.createdBy = deliveryCharge.getCreatedBy();
		this.datetimeCreated = deliveryCharge.getDateTimeCreated();
		this.active = deliveryCharge.isActive();
		this.lastModifiedBy = deliveryCharge.getLastModifiedBy();
		this.dateTimeModified = deliveryCharge.getDateTimeModified();
	}

	public String getDestination() {
		return destination;
	}

	public long getDeliveryCharge() {
		return deliveryCharge;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getDatetimeCreated() {
		return datetimeCreated;
	}

	public boolean isActive() {
		return active;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public LocalDateTime getDateTimeModified() {
		return dateTimeModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((dateTimeModified == null) ? 0 : dateTimeModified.hashCode());
		result = prime * result + ((datetimeCreated == null) ? 0 : datetimeCreated.hashCode());
		result = prime * result + (int) (deliveryCharge ^ (deliveryCharge >>> 32));
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
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
		DeliveryDestinationAndChargeDto other = (DeliveryDestinationAndChargeDto) obj;
		if (active != other.active)
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (dateTimeModified == null) {
			if (other.dateTimeModified != null)
				return false;
		} else if (!dateTimeModified.equals(other.dateTimeModified))
			return false;
		if (datetimeCreated == null) {
			if (other.datetimeCreated != null)
				return false;
		} else if (!datetimeCreated.equals(other.datetimeCreated))
			return false;
		if (deliveryCharge != other.deliveryCharge)
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (lastModifiedBy == null) {
			if (other.lastModifiedBy != null)
				return false;
		} else if (!lastModifiedBy.equals(other.lastModifiedBy))
			return false;
		return true;
	}

}
