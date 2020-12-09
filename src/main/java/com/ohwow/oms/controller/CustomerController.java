package com.ohwow.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.customer.dto.CustomerDto;
import com.ohwow.oms.customer.service.CustomerService;

@RestController
@RequestMapping(path = "api/test")
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/customers")
	public List<CustomerDto> getAllCustomers() {
		return customerService.getAllCustomers();
	}
}
