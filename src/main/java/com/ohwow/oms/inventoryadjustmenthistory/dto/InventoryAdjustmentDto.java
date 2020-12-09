package com.ohwow.oms.inventoryadjustmenthistory.dto;

import java.time.LocalDateTime;

import com.ohwow.oms.products.domain.Product;

public class InventoryAdjustmentDto {

	private final Product product;
	private final int adjustedStockOnHand;
	private final String adjustedBy;
	private final LocalDateTime dateTimeAdjusted;
	private final String notes;

	public InventoryAdjustmentDto(Product product, int adjustedStockOnHand, String adjustedBy,
			LocalDateTime dateTimeAdjusted, String notes) {
		this.product = product;
		this.adjustedStockOnHand = adjustedStockOnHand;
		this.adjustedBy = adjustedBy;
		this.dateTimeAdjusted = dateTimeAdjusted;
		this.notes = notes;
	}

	public Product getProduct() {
		return product;
	}

	public int getAdjustedStockOnHand() {
		return adjustedStockOnHand;
	}

	public String getAdjustedBy() {
		return adjustedBy;
	}

	public LocalDateTime getDateTimeAdjusted() {
		return dateTimeAdjusted;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjustedBy == null) ? 0 : adjustedBy.hashCode());
		result = prime * result + adjustedStockOnHand;
		result = prime * result + ((dateTimeAdjusted == null) ? 0 : dateTimeAdjusted.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		InventoryAdjustmentDto other = (InventoryAdjustmentDto) obj;
		if (adjustedBy == null) {
			if (other.adjustedBy != null)
				return false;
		} else if (!adjustedBy.equals(other.adjustedBy))
			return false;
		if (adjustedStockOnHand != other.adjustedStockOnHand)
			return false;
		if (dateTimeAdjusted == null) {
			if (other.dateTimeAdjusted != null)
				return false;
		} else if (!dateTimeAdjusted.equals(other.dateTimeAdjusted))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
