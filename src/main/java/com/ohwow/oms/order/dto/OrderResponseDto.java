package com.ohwow.oms.order.dto;

import java.util.List;

public class OrderResponseDto {
	private final int pages;
	private final List<OrderDto> orders;

	public OrderResponseDto(int pages, List<OrderDto> orders) {
		this.pages = pages;
		this.orders = orders;
	}

	public int getPages() {
		return pages;
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		OrderResponseDto other = (OrderResponseDto) obj;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (pages != other.pages)
			return false;
		return true;
	}

}
