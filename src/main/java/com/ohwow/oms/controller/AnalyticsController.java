package com.ohwow.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.analytics.dto.OrderActivityDto;
import com.ohwow.oms.analytics.dto.SalesActivitySummaryDto;
import com.ohwow.oms.analytics.service.AnalyticsService;
import com.ohwow.oms.commons.exceptions.InvalidTimeUnitException;
import com.ohwow.oms.commons.timeunit.TimeUnitEnum;

@RestController
@RequestMapping(path = "api/v1/analytics")
@CrossOrigin
public class AnalyticsController {

	@Autowired
	AnalyticsService analyticsService;

	@GetMapping("/orderactivity")
	public List<OrderActivityDto> getOrderActivity(@RequestParam TimeUnitEnum timeUnit)
			throws InvalidTimeUnitException {
		return analyticsService.getOrderActivityByDateRange(timeUnit);
	}

	@GetMapping("/salesactivity")
	public SalesActivitySummaryDto getSalesActivitySummary(@RequestParam TimeUnitEnum timeUnit)
			throws InvalidTimeUnitException {
		return analyticsService.getSalesActivitySummaryByDateRange(timeUnit);
	}
}
