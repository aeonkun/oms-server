package com.ohwow.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.order.exception.OrderException;
import com.ohwow.oms.statushistory.dto.OrderStatusAndStatusHistoryDto;
import com.ohwow.oms.statushistory.service.StatusHistoryService;

@RestController
@RequestMapping(path = "api/v1/")
@CrossOrigin
public class StatusHistoryController {

	@Autowired
	StatusHistoryService statusHistoryService;

	@GetMapping("orders/{id}/status")
	public OrderStatusAndStatusHistoryDto getOrderStatusAndStatusHistory(@PathVariable long id) throws OrderException {

		return statusHistoryService.getOrderStatusAndHistory(id);
	}

//	@PutMapping("/{id}/status")
//	public boolean updateOrderStatusAndHistory(@PathVariable long id, @RequestBody OrderStatusEnum orderStatus,
//			Authentication authentication) throws OrderException {
//
//		return statusHistoryService.updateOrderStatusAndHistory(id, orderStatus, authentication.getName());
//	}

}
