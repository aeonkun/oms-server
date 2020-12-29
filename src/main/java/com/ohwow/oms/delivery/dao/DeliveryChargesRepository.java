package com.ohwow.oms.delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.delivery.domain.DeliveryDestinationAndCharge;

@Repository
public interface DeliveryChargesRepository extends JpaRepository<DeliveryDestinationAndCharge, Long> {

}
