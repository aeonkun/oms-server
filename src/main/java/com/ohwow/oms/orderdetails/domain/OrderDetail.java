package com.ohwow.oms.orderdetails.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.orderdetails.dto.OrderDetailDto;
import com.ohwow.oms.products.domain.Product;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order orderId;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private int quantity;
	private long total;

	public OrderDetail() {
		// default constructor
	}

	public OrderDetail(Order order, Product product, int quantity, long total) {
		this.orderId = order;
		this.product = product;
		this.quantity = quantity;
		this.total = total;
	}

	public OrderDetail(Order order, OrderDetailDto orderDetailDto, Product product) {
		this.orderId = order;
		this.product = product;
		this.quantity = orderDetailDto.getQuantity();
		this.total = orderDetailDto.getTotal();
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
