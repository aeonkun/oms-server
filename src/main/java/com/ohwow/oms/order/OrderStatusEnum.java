package com.ohwow.oms.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OrderStatusEnum {
	@JsonProperty("ORDER_PLACED")
	ORDER_PLACED("ORDER_PLACED"),

	@JsonProperty("CONFIRMED")
	CONFIRMED("CONFIRMED"),

	@JsonProperty("FOR_DELIVERY")
	FOR_DELIVERY("FOR_DELIVERY"),

	@JsonProperty("COMPLETED")
	COMPLETED("COMPLETED"),

	@JsonProperty("CANCELLED")
	CANCELLED("CANCELLED");

	private String orderStatus;

	private OrderStatusEnum(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@JsonCreator
	public static OrderStatusEnum forValue(@JsonProperty("orderStatus") String orderStatus) {
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
			if (orderStatusEnum.orderStatus.equals(orderStatus)) {
				return orderStatusEnum;
			}
		}

		return null;
	}

}
