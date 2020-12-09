package com.ohwow.oms.inventory.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public final class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long productId;
	private int stockOnHand;
	private int committedStock;
	private int availableStock;

	public Inventory() {

	}

	public Inventory(long productId, int stockOnHand, int committedStock, int availableStock) {
		this.productId = productId;
		this.stockOnHand = stockOnHand;
		this.committedStock = committedStock;
		this.availableStock = availableStock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getStockOnHand() {
		return stockOnHand;
	}

	public void setStockOnHand(int stockOnHand) {
		this.stockOnHand = stockOnHand;
	}

	public int getCommittedStock() {
		return committedStock;
	}

	public void setCommittedStock(int committedStock) {
		this.committedStock = committedStock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

}
