package com.ohwow.oms.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ohwow.oms.customer.domain.Customer;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.orderdetails.domain.OrderDetail;
import com.ohwow.oms.statushistory.domain.StatusHistory;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	private String createdBy;
	private LocalDateTime dateTimeCreated;
	private String modifiedBy;
	private LocalDateTime dateTimeModified;
	private long totalPrice;
	private String paymentMethod;
	private String additionalNotes;
	private LocalDateTime dateTimeCompleted;

	@Enumerated(EnumType.STRING)
	private OrderStatusEnum orderStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderDetail> ordeDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<StatusHistory> statusHistories;

	public Order() {
		// default constructor
	}

	public Order(Customer customer, String createdBy, LocalDateTime dateTimeCreated, String modifiedBy,
			LocalDateTime dateTimeModified, long totalPrice, OrderStatusEnum orderStatus, String additionalNotes,
			String paymentMethod, LocalDateTime dateTimeCompleted) {
		this.customer = customer;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.additionalNotes = additionalNotes;
		this.paymentMethod = paymentMethod;
		this.dateTimeCompleted = dateTimeCompleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderDetail> getOrdeDetails() {
		return ordeDetails;
	}

	public void setOrdeDetails(List<OrderDetail> ordeDetails) {
		this.ordeDetails = ordeDetails;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public LocalDateTime getDateTimeCompleted() {
		return dateTimeCompleted;
	}

	public void setDateTimeCompleted(LocalDateTime dateTimeCompleted) {
		this.dateTimeCompleted = dateTimeCompleted;
	}

}
