package com.ohwow.oms.products.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.products.dto.ProductDto;

@Entity
@Table(name = "products")
public final class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String itemName;
	private long price;
	private String createdBy;
	private LocalDateTime dateTimeCreated;
	private String modifiedBy;
	private LocalDateTime dateTimeModified;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Inventory inventory;

	public Product() {
		// Default constructor
	}

	public Product(String itemName, long price, String createdBy, LocalDateTime dateTimeCreated, String modifiedBy,
			LocalDateTime dateTimeModified) {
		this.itemName = itemName;
		this.price = price;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
	}

	public Product(ProductDto product) {
		this.itemName = product.getItemName();
		this.price = product.getPrice();
		this.createdBy = product.getCreatedBy();
		this.dateTimeCreated = product.getDateTimeCreated();
		this.modifiedBy = product.getModifiedBy();
		this.dateTimeModified = product.getDateTimeModified();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
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

}
