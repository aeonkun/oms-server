package com.ohwow.oms.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PaymentMethodEnum {
	GCASH("GCASH"), CASH_ON_DELIVERY("CASH_ON_DELIVERY");

	private String paymentMethod;

	private PaymentMethodEnum(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@JsonCreator
	public static PaymentMethodEnum forValue(@JsonProperty("paymentMethod") String paymentMethod) {
		for (PaymentMethodEnum paymentMethodEnum : PaymentMethodEnum.values()) {
			if (paymentMethodEnum.paymentMethod.equals(paymentMethod)) {
				return paymentMethodEnum;
			}
		}

		return null;
	}
}
