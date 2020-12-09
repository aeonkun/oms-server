package com.ohwow.oms.products.dto;

import java.time.LocalDateTime;

import com.ohwow.oms.products.domain.Product;

public class ProductDto {
	private long id;
	private String itemName;
	private long price;
	private int stockOnHand;
	private String createdBy;
	private LocalDateTime dateTimeCreated;
	private String modifiedBy;
	private LocalDateTime dateTimeModified;

	public ProductDto(long id, String itemName, long price, String createdBy, int stockOnHand,
			LocalDateTime dateTimeCreated, String modifiedBy, LocalDateTime dateTimeModified) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.stockOnHand = stockOnHand;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
	}

	public ProductDto(Product product) {
		this.id = product.getId();
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

	public int getStockOnHand() {
		return stockOnHand;
	}

	public void setStockOnHand(int stockOnHand) {
		this.stockOnHand = stockOnHand;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((dateTimeCreated == null) ? 0 : dateTimeCreated.hashCode());
		result = prime * result + ((dateTimeModified == null) ? 0 : dateTimeModified.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + stockOnHand;
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
		ProductDto other = (ProductDto) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
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
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (price != other.price)
			return false;
		if (stockOnHand != other.stockOnHand)
			return false;
		return true;
	}

}
