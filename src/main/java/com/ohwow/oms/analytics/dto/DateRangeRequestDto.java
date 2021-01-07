package com.ohwow.oms.analytics.dto;

import java.time.LocalDateTime;

public class DateRangeRequestDto {

	private final LocalDateTime startDate;
	private final LocalDateTime endDate;

	public DateRangeRequestDto(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

}
