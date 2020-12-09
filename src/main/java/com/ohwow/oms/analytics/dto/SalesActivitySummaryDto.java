package com.ohwow.oms.analytics.dto;

import java.util.List;

public class SalesActivitySummaryDto {
	private final List<SalesActivityDto> salesActivities;
	private final double totalSales;

	public SalesActivitySummaryDto(List<SalesActivityDto> salesActivities, double totalSales) {
		this.salesActivities = salesActivities;
		this.totalSales = totalSales;
	}

	public List<SalesActivityDto> getSalesActivities() {
		return salesActivities;
	}

	public double getTotalSales() {
		return totalSales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salesActivities == null) ? 0 : salesActivities.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalSales);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SalesActivitySummaryDto other = (SalesActivitySummaryDto) obj;
		if (salesActivities == null) {
			if (other.salesActivities != null)
				return false;
		} else if (!salesActivities.equals(other.salesActivities))
			return false;
		if (Double.doubleToLongBits(totalSales) != Double.doubleToLongBits(other.totalSales))
			return false;
		return true;
	}

}
