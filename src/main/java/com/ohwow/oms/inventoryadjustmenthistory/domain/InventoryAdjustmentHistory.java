package com.ohwow.oms.inventoryadjustmenthistory.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto;

@Entity
@Table(name = "inventory_adjustment_history")
public final class InventoryAdjustmentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long productId;
	private int adjustedStockOnHand;
	private String adjustedBy;
	private LocalDateTime dateTimeAdjusted;
	private String notes;

	public InventoryAdjustmentHistory() {
	}

	public InventoryAdjustmentHistory(long productId, int amount, String adjustedBy, LocalDateTime dateTimeAdjusted,
			String notes) {
		this.productId = productId;
		this.adjustedStockOnHand = amount;
		this.adjustedBy = adjustedBy;
		this.dateTimeAdjusted = dateTimeAdjusted;
		this.notes = notes;
	}

	public InventoryAdjustmentHistory(InventoryAdjustmentDto inventoryAdjustmentDto) {
		this.productId = inventoryAdjustmentDto.getProduct().getId();
		this.adjustedStockOnHand = inventoryAdjustmentDto.getAdjustedStockOnHand();
		this.adjustedBy = inventoryAdjustmentDto.getAdjustedBy();
		this.dateTimeAdjusted = inventoryAdjustmentDto.getDateTimeAdjusted();
		this.notes = inventoryAdjustmentDto.getNotes();
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

	public int getAdjustedStockOnHand() {
		return adjustedStockOnHand;
	}

	public void setAdjustedStockOnHand(int amount) {
		this.adjustedStockOnHand = amount;
	}

	public String getAdjustedBy() {
		return adjustedBy;
	}

	public void setAdjustedBy(String adjustedBy) {
		this.adjustedBy = adjustedBy;
	}

	public LocalDateTime getDateTimeAdjusted() {
		return dateTimeAdjusted;
	}

	public void setDateTimeAdjusted(LocalDateTime dateTimeAdjusted) {
		this.dateTimeAdjusted = dateTimeAdjusted;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
