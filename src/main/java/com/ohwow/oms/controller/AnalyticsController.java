package com.ohwow.oms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.analytics.dto.OrderActivityDto;
import com.ohwow.oms.analytics.dto.ProductSalesDto;
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
	public List<OrderActivityDto> getOrderActivity(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

		return analyticsService.getOrderActivityByDateRange(startDate, endDate);
	}

	@GetMapping("/salesactivity")
	public SalesActivitySummaryDto getSalesActivitySummary(@RequestParam TimeUnitEnum timeUnit)
			throws InvalidTimeUnitException {
		return analyticsService.getSalesActivitySummaryByDateRange(timeUnit);
	}

	@GetMapping("/productsales")
	public List<ProductSalesDto> getProductSales(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return analyticsService.getProductSalesByCompletedDate(startDate, endDate);
	}
}
