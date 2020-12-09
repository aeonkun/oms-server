package com.ohwow.oms.order.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Page<Order> findAllByOrderByIdDesc(Pageable pageable);

	List<Order> findAllByDateTimeCreatedBetween(LocalDateTime dateTimeCreatedStart, LocalDateTime dateTimeCreatedEnd);

	List<Order> findAllByDateTimeCompletedBetween(LocalDateTime dateCompletedStart, LocalDateTime dateTimeCompletedEnd);
}
