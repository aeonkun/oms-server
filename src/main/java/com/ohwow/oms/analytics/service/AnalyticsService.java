package com.ohwow.oms.analytics.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.analytics.dto.OrderActivityDto;
import com.ohwow.oms.analytics.dto.ProductSalesDto;
import com.ohwow.oms.analytics.dto.ProductSalesProjection;
import com.ohwow.oms.analytics.dto.SalesActivityDto;
import com.ohwow.oms.commons.exceptions.InvalidTimeUnitException;
import com.ohwow.oms.commons.timeunit.TimeUnitEnum;
import com.ohwow.oms.commons.timeunit.dto.StartDateAndEndDateDto;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.dao.OrderRepository;
import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.products.dao.ProductRepository;
import com.ohwow.oms.products.domain.Product;

@Service
@Transactional(readOnly = true)
public class AnalyticsService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	/**
	 * Get the order activity based on the given time unit.
	 * 
	 * @param timeUnit
	 * @return
	 * @throws InvalidTimeUnitException
	 */
	public List<OrderActivityDto> getOrderActivityByDateRange(LocalDateTime startDate, LocalDateTime endDate) {

		List<Order> orders = orderRepository.findAllByDateTimeCreatedBetween(startDate, endDate);

		List<OrderActivityDto> orderActivityDtos = new ArrayList<>();

		for (OrderStatusEnum orderStatus : OrderStatusEnum.values()) {
			long count = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).count();
			OrderActivityDto orderActivityDto = new OrderActivityDto(orderStatus, count);
			orderActivityDtos.add(orderActivityDto);
		}

		return orderActivityDtos;
	}

	public List<SalesActivityDto> getSalesActivitySummaryByDateRange(int month, int year)
			throws InvalidTimeUnitException {

		List<SalesActivityDto> salesActivityDtos = new ArrayList<>();

		if (year != 0 && month == 0) {

			salesActivityDtos = orderRepository.findAllCompletedOrdersPerMonthByYear(year);

			for (int i = 1; i <= 12; i++) {
				String monthString = Month.of(i).name();

				// insert month with zero value if absent in the data result
				if (salesActivityDtos.stream().noneMatch(data -> monthString.equals(data.getDateUnit()))) {
					salesActivityDtos.add(i - 1, new SalesActivityDto(i, 0));
				}
			}

		} else {
			List<LocalDate> dates = LocalDate.of(year, month, 1)
					.datesUntil(LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()))
					.collect(Collectors.toList());

			for (LocalDate date : dates) {
				List<Order> orders = orderRepository.findAllByDateTimeCompletedBetween(
						LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
				long value = orders.stream().mapToLong(Order::getTotalPrice).sum();
				salesActivityDtos.add(new SalesActivityDto(date.format(DateTimeFormatter.ofPattern("MM/dd")), value));
			}
		}

		return salesActivityDtos;

	}

	public List<ProductSalesDto> getProductSalesByCompletedDate(LocalDateTime startDate, LocalDateTime endDate) {

		List<Product> activeProducts = productRepository.findByActiveTrue();

		Collection<ProductSalesProjection> productSales = productRepository.findAllProductSalesByDateRange(startDate,
				endDate);

		List<ProductSalesDto> productSalesDtos = productSales.stream().map(ProductSalesDto::new)
				.collect(Collectors.toList());

		for (Product product : activeProducts) {

			if (!productSalesDtos.stream().anyMatch(p -> p.getProductName().equals(product.getItemName()))) {
				productSalesDtos.add(new ProductSalesDto(product.getItemName(), 0));
			}
		}

		return productSalesDtos;

	}

	/**
	 * Get the start date and end date for the date range.
	 * 
	 * @param timeUnit
	 * @return
	 * @throws InvalidTimeUnitException
	 */
	private StartDateAndEndDateDto getStartDateAndEndDate(TimeUnitEnum timeUnit) throws InvalidTimeUnitException {
		LocalDateTime dateTimeCreatedStart;
		LocalDateTime dateTimeCreatedEnd;

		switch (timeUnit) {
		case DAILY:
			dateTimeCreatedStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
			dateTimeCreatedEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
			break;
		case WEEKLY:
			dateTimeCreatedStart = LocalDateTime
					.of(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)), LocalTime.MIN);
			dateTimeCreatedEnd = LocalDateTime
					.of(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)), LocalTime.MAX);
			break;
		case MONTHLY:
			dateTimeCreatedStart = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()),
					LocalTime.MIN);
			dateTimeCreatedEnd = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()),
					LocalTime.MAX);
			break;
		case ANNUALLY:
			dateTimeCreatedStart = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()),
					LocalTime.MIN);
			dateTimeCreatedEnd = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()),
					LocalTime.MAX);

			break;

		default:
			throw new InvalidTimeUnitException(InvalidTimeUnitException.INVALID_TIME_UNIT_EXCEPTION);
		}

		return new StartDateAndEndDateDto(dateTimeCreatedStart, dateTimeCreatedEnd);
	}

}
