package com.ohwow.oms.analytics.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.analytics.dto.OrderActivityDto;
import com.ohwow.oms.analytics.dto.ProductSalesDto;
import com.ohwow.oms.analytics.dto.SalesActivityDto;
import com.ohwow.oms.analytics.dto.SalesActivitySummaryDto;
import com.ohwow.oms.commons.exceptions.InvalidTimeUnitException;
import com.ohwow.oms.commons.timeunit.TimeUnitEnum;
import com.ohwow.oms.commons.timeunit.dto.StartDateAndEndDateDto;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.dao.OrderRepository;
import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.orderdetails.domain.OrderDetail;
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
	public List<OrderActivityDto> getOrderActivityByDateRange(TimeUnitEnum timeUnit) throws InvalidTimeUnitException {

		StartDateAndEndDateDto startDateAndEndDateDto = getStartDateAndEndDate(timeUnit);

		List<Order> orders = orderRepository.findAllByDateTimeCreatedBetween(startDateAndEndDateDto.getStartDateTime(),
				startDateAndEndDateDto.getEndDateTime());

		List<OrderActivityDto> orderActivityDtos = new ArrayList<>();

		for (OrderStatusEnum orderStatus : OrderStatusEnum.values()) {
			long count = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).count();
			OrderActivityDto orderActivityDto = new OrderActivityDto(orderStatus, count);
			orderActivityDtos.add(orderActivityDto);
		}

		return orderActivityDtos;
	}

	public SalesActivitySummaryDto getSalesActivitySummaryByDateRange(TimeUnitEnum timeUnit)
			throws InvalidTimeUnitException {

		// NOTE: DAILY time unit should show weekly data in the graph instead of only
		// one date
		timeUnit = TimeUnitEnum.DAILY.equals(timeUnit) ? TimeUnitEnum.WEEKLY : timeUnit;

		StartDateAndEndDateDto startDateAndEndDateDto = getStartDateAndEndDate(timeUnit);
		List<SalesActivityDto> salesActivityDtos = new ArrayList<>();
		double totalSales = 0;

		if (TimeUnitEnum.WEEKLY.equals(timeUnit) || TimeUnitEnum.MONTHLY.equals(timeUnit)) {

			List<LocalDate> dates = startDateAndEndDateDto.getStartDateTime().toLocalDate()
					.datesUntil(startDateAndEndDateDto.getEndDateTime().toLocalDate()).collect(Collectors.toList());

			for (LocalDate date : dates) {
				List<Order> orders = orderRepository.findAllByDateTimeCompletedBetween(
						LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
				long value = orders.stream().mapToLong(Order::getTotalPrice).sum();
				salesActivityDtos.add(new SalesActivityDto(date.format(DateTimeFormatter.ofPattern("MM/dd")), value));
				totalSales += value;
			}

		} else if (TimeUnitEnum.ANNUALLY.equals(timeUnit)) {
			int year = LocalDate.now().getYear();

			salesActivityDtos = orderRepository.findAllCompletedOrdersPerMonthByYear(year);
			totalSales = salesActivityDtos.stream().mapToDouble(SalesActivityDto::getValue).sum();

			for (int i = 1; i <= 12; i++) {
				String monthString = Month.of(i).name();

				// insert month with zero value if absent in the data result
				if (!salesActivityDtos.stream().anyMatch(data -> monthString.equals(data.getDateUnit()))) {
					salesActivityDtos.add(i - 1, new SalesActivityDto(i, 0));
				}
			}

		}

		return new SalesActivitySummaryDto(salesActivityDtos, totalSales);

	}

	public List<ProductSalesDto> getProductSalesByCompletedDate(TimeUnitEnum timeUnitEnum)
			throws InvalidTimeUnitException {

		StartDateAndEndDateDto startDateAndEndDateDto = getStartDateAndEndDate(timeUnitEnum);

		List<Product> products = productRepository.findAll();

		List<Order> orders = orderRepository.findAllByDateTimeCreatedBetween(startDateAndEndDateDto.getStartDateTime(),
				startDateAndEndDateDto.getEndDateTime());

		List<ProductSalesDto> productSales = new ArrayList<>();

		for (Product product : products) {

			int quantitySold = 0;

			for (Order order : orders) {

				List<OrderDetail> orderDetails = order.getOrdeDetails();

				Optional<OrderDetail> matchingProduct = orderDetails.stream()
						.filter(od -> od.getProduct().getId() == product.getId()).findFirst();

				if (matchingProduct.isPresent()) {
					quantitySold += matchingProduct.get().getQuantity();
				}

			}

			ProductSalesDto productSalesDto = new ProductSalesDto(product.getItemName(), quantitySold);
			productSales.add(productSalesDto);
		}

		return productSales;

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
