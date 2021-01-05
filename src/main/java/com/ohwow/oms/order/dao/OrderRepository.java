package com.ohwow.oms.order.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.analytics.dto.SalesActivityDto;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Page<Order> findAllByOrderByIdDesc(Pageable pageable);

	List<Order> findAllByDateTimeCreatedBetween(LocalDateTime dateTimeCreatedStart, LocalDateTime dateTimeCreatedEnd);

	List<Order> findAllByDateTimeCompletedBetween(LocalDateTime dateCompletedStart, LocalDateTime dateTimeCompletedEnd);

	List<Order> findByHasStockIssuesTrue();

	List<Order> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

	@Query("select new com.ohwow.oms.analytics.dto.SalesActivityDto(MONTH(o.dateTimeCompleted), SUM(o.totalPrice)) from Order o where o.orderStatus = 'COMPLETED' AND YEAR(o.dateTimeCompleted) = ?1 group by MONTH(o.dateTimeCompleted) order by MONTH(o.dateTimeCompleted) ASC")
	List<SalesActivityDto> findAllCompletedOrdersPerMonthByYear(int year);
}
