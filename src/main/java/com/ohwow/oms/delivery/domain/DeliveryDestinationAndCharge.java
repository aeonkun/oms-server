package com.ohwow.oms.delivery.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ohwow.oms.delivery.dto.DeliveryDestinationAndChargeDto;

@Entity
@Table(name = "delivery_charges")
public class DeliveryDestinationAndCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String destination;
	private long deliveryCharge;
	private String createdBy;
	private LocalDateTime dateTimeCreated;
	private boolean active;
	private String lastModifiedBy;
	private LocalDateTime dateTimeModified;

	public DeliveryDestinationAndCharge() {
	}

	public DeliveryDestinationAndCharge(long id, String destination, long deliveryCharge, String createdBy,
			LocalDateTime dateTimeCreated, boolean active, String lastModifiedBy, LocalDateTime dateTimeModified) {
		this.id = id;
		this.destination = destination;
		this.deliveryCharge = deliveryCharge;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.active = active;
		this.lastModifiedBy = lastModifiedBy;
		this.dateTimeModified = dateTimeModified;
	}

	public DeliveryDestinationAndCharge(DeliveryDestinationAndChargeDto deliveryDestinationAndChargeDto) {
		this.destination = deliveryDestinationAndChargeDto.getDestination();
		this.deliveryCharge = deliveryDestinationAndChargeDto.getDeliveryCharge();
		this.createdBy = deliveryDestinationAndChargeDto.getCreatedBy();
		this.dateTimeCreated = deliveryDestinationAndChargeDto.getDatetimeCreated();
		this.active = deliveryDestinationAndChargeDto.isActive();
		this.lastModifiedBy = deliveryDestinationAndChargeDto.getLastModifiedBy();
		this.dateTimeModified = deliveryDestinationAndChargeDto.getDateTimeModified();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(long deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getDateTimeModified() {
		return dateTimeModified;
	}

	public void setDateTimeModified(LocalDateTime dateTimeModified) {
		this.dateTimeModified = dateTimeModified;
	}

}
