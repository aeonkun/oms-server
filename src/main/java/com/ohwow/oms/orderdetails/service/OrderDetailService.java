package com.ohwow.oms.orderdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.orderdetails.dao.OrderDetailRepository;
import com.ohwow.oms.orderdetails.domain.OrderDetail;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	public List<OrderDetail> getOrderDetailByOrderId(Order order) {

		return orderDetailRepository.findByOrderIdOrderByIdDesc(order);
	}

	public List<OrderDetail> getAllOrderWithNotEnoughStock() {
		return orderDetailRepository.findByHasEnoughStockFalse();
	}

	public void updateOrderStocks(long orderId) {

	}
}
