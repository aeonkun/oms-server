package com.ohwow.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.inventory.exception.InventoryException;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.dto.OrderDto;
import com.ohwow.oms.order.dto.OrderResponseDto;
import com.ohwow.oms.order.exception.OrderException;
import com.ohwow.oms.order.service.OrderService;
import com.ohwow.oms.products.exception.ProductException;

@RestController
@RequestMapping(path = "api/v1/orders")
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping
	public OrderResponseDto getAllOrders(@RequestParam int page, @RequestParam int rows) {

		return orderService.getAllOrdersTest(page, rows);
	}

	@GetMapping("/{id}")
	public OrderDto getOrder(@PathVariable long id) throws OrderException {
		return orderService.getOrderById(id);
	}

	@PostMapping
	public long createOrder(@RequestBody OrderDto orderDto)
			throws ProductException, OrderException, InventoryException {

		return orderService.createOrder(orderDto);
	}

	@PutMapping("/{id}/status")
	public boolean updateOrderStatus(@PathVariable long id, @RequestBody OrderStatusEnum orderStatus,
			Authentication authentication) throws InventoryException, ProductException {

		return orderService.updateOrderStatus(id, orderStatus, authentication.getName());
	}

}
