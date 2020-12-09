package com.ohwow.oms.orderdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohwow.oms.orderdetails.dao.OrderDetailRepository;
import com.ohwow.oms.orderdetails.domain.OrderDetail;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	public List<OrderDetail> getOrderDetailByOrderId(long id) {

		return orderDetailRepository.findByOrderIdOrderByIdDesc(id);
	}
}
