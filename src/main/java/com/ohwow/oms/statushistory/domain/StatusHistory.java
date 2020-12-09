package com.ohwow.oms.statushistory.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.domain.Order;

@Entity
@Table(name = "status_history")
public class StatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order orderId;
	private OrderStatusEnum oldStatus;
	private OrderStatusEnum newStatus;
	private String modifiedBy;
	private LocalDateTime dateTimeModified;

	public StatusHistory() {
		// default constructor
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return orderId;
	}

	public void setOrder(Order order) {
		this.orderId = order;
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

}
