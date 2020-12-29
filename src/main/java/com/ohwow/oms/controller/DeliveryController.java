package com.ohwow.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.delivery.dto.DeliveryDestinationAndChargeDto;
import com.ohwow.oms.delivery.exception.DeliveryException;
import com.ohwow.oms.delivery.service.DeliveryService;

@RestController
@RequestMapping(path = "api/v1/delivery")
@CrossOrigin
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;

	@GetMapping("/destinations")
	public List<DeliveryDestinationAndChargeDto> getDeliveryDestinationsAndCharges() {

		return deliveryService.getAllDeliveryCharges();
	}

	@PostMapping("/destinations/create")
	public boolean createDeliveryDestinationAndCharge(
			@RequestBody DeliveryDestinationAndChargeDto deliveryDestinationAndChargeDto) throws DeliveryException {

		return deliveryService.createDeliveryDestinationAndCharge(deliveryDestinationAndChargeDto);
	}

}
