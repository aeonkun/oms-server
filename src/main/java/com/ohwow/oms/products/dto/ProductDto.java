package com.ohwow.oms.products.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohwow.oms.products.domain.Product;

public class ProductDto {
	private final long id;
	private final String itemName;
	private final long price;
	private final int stockOnHand;
	private final String createdBy;
	private final LocalDateTime dateTimeCreated;
	private final String modifiedBy;
	private final LocalDateTime dateTimeModified;

	@JsonCreator
	public ProductDto(@JsonProperty("id") long id, @JsonProperty("itemName") String itemName,
			@JsonProperty("price") long price, @JsonProperty("createdBy") String createdBy,
			@JsonProperty("stockOnHand") int stockOnHand, LocalDateTime dateTimeCreated, String modifiedBy,
			@JsonProperty("dateTimeModified") LocalDateTime dateTimeModified) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.stockOnHand = stockOnHand;
		this.createdBy = createdBy;
		this.dateTimeCreated = dateTimeCreated;
		this.modifiedBy = modifiedBy;
		this.dateTimeModified = dateTimeModified;
	}

	public ProductDto(Product product, int stockOnHand) {
		this.id = product.getId();
		this.itemName = product.getItemName();
		this.price = product.getPrice();
		this.createdBy = product.getCreatedBy();
		this.dateTimeCreated = product.getDateTimeCreated();
		this.modifiedBy = product.getModifiedBy();
		this.dateTimeModified = product.getDateTimeModified();
		this.stockOnHand = stockOnHand;

	}

	public long getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public long getPrice() {
		return price;
	}

	public int getStockOnHand() {
		return stockOnHand;
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
