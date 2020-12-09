package com.ohwow.oms.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.customer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
