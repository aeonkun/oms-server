package com.ohwow.oms.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.ohwow.oms.customer.dao.CustomerRepository;
import com.ohwow.oms.customer.domain.Customer;
import com.ohwow.oms.customer.dto.CustomerDto;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<CustomerDto> getAllCustomers() {

		List<CustomerDto> customers = customerRepository.findAll().stream().map(x -> new CustomerDto(x))
				.collect(Collectors.toList());

		return customers;
	};

	public Customer createCustomer(CustomerDto customerDto) {

		Customer customer = new Customer();

		if (!ObjectUtils.isEmpty(customerDto)) {
			customer = customerRepository.save(new Customer(customerDto));
		}

		return customer;
	}
}
