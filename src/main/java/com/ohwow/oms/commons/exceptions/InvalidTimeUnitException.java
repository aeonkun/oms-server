package com.ohwow.oms.commons.exceptions;

public class InvalidTimeUnitException extends Exception {
	public static final String INVALID_TIME_UNIT_EXCEPTION = "Invalid Time Unit";

	/**
	 * 
	 */
	private static final long serialVersionUID = 532889636740786171L;

	public InvalidTimeUnitException(String message) {
		super(message);
	}

}
