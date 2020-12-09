package com.ohwow.oms.commons.timeunit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TimeUnitEnum {

	@JsonProperty("DAILY")
	DAILY("DAILY"),

	@JsonProperty("WEEKLY")
	WEEKLY("WEEKLY"),

	@JsonProperty("MONTHLY")
	MONTHLY("MONTHLY"),

	@JsonProperty("ANNUALLY")
	ANNUALLY("ANNUALLY");

	private String timeUnit;

	private TimeUnitEnum(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	@JsonCreator
	public static TimeUnitEnum forValue(@JsonProperty("timeUnit") String timeUnit) {
		for (TimeUnitEnum timeUnitEnum : TimeUnitEnum.values()) {
			if (timeUnitEnum.timeUnit.equals(timeUnit)) {
				return timeUnitEnum;
			}
		}

		return null;
	}

}
