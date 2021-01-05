package com.ohwow.oms.analytics.dto;

import java.time.Month;

public class SalesActivityDto {

	private final String dateUnit;

	private final double value;

	public SalesActivityDto(int dateUnit, long value) {
		this.dateUnit = Month.of(dateUnit).name();
		this.value = value / 100;
	}

	public SalesActivityDto(String dateUnit, double value) {
		this.dateUnit = dateUnit;
		this.value = value / 100;
	}

	public String getDateUnit() {
		return dateUnit;
	}

	public double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateUnit == null) ? 0 : dateUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
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
		SalesActivityDto other = (SalesActivityDto) obj;
		if (dateUnit == null) {
			if (other.dateUnit != null)
				return false;
		} else if (!dateUnit.equals(other.dateUnit))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

}
