package com.ohwow.oms.products.dto;

public class ProductAndInventoryDto {

	private final long id;
	private final String itemName;
	private final long price;
	private final int stockOnHand;
	private final int committedStock;
	private final int availableStock;

	public ProductAndInventoryDto(long id, String itemName, long price, int stockOnHand, int committedStock,
			int availableStock) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.stockOnHand = stockOnHand;
		this.committedStock = committedStock;
		this.availableStock = availableStock;
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

	public int getCommittedStock() {
		return committedStock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableStock;
		result = prime * result + committedStock;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
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
		ProductAndInventoryDto other = (ProductAndInventoryDto) obj;
		if (availableStock != other.availableStock)
			return false;
		if (committedStock != other.committedStock)
			return false;
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (price != other.price)
			return false;
		if (stockOnHand != other.stockOnHand)
			return false;
		return true;
	}

}
