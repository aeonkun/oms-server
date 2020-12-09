package com.ohwow.oms.inventoryadjustmenthistory.dto;

import java.util.List;

public class InventoryAdjustmentHistoryResponse {
	private final int pages;
	private final List<InventoryAdjustmentDto> adjustments;

	public InventoryAdjustmentHistoryResponse(int pages, List<InventoryAdjustmentDto> adjustments) {
		this.pages = pages;
		this.adjustments = adjustments;
	}

	public int getPages() {
		return pages;
	}

	public List<InventoryAdjustmentDto> getAdjustments() {
		return adjustments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjustments == null) ? 0 : adjustments.hashCode());
		result = prime * result + pages;
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
		InventoryAdjustmentHistoryResponse other = (InventoryAdjustmentHistoryResponse) obj;
		if (adjustments == null) {
			if (other.adjustments != null)
				return false;
		} else if (!adjustments.equals(other.adjustments))
			return false;
		if (pages != other.pages)
			return false;
		return true;
	}

}
