package com.ohwow.oms.delivery.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.delivery.dao.DeliveryChargesRepository;
import com.ohwow.oms.delivery.domain.DeliveryDestinationAndCharge;
import com.ohwow.oms.delivery.dto.DeliveryDestinationAndChargeDto;
import com.ohwow.oms.delivery.exception.DeliveryException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DeliveryService {

	@Autowired
	DeliveryChargesRepository deliveryChargesRepository;

	public List<DeliveryDestinationAndChargeDto> getAllDeliveryCharges() {

		List<DeliveryDestinationAndCharge> deliveryCharges = deliveryChargesRepository.findAll();

		return deliveryCharges.stream().map(DeliveryDestinationAndChargeDto::new).collect(Collectors.toList());
	}

	public boolean createDeliveryDestinationAndCharge(DeliveryDestinationAndChargeDto deliveryChargeDto)
			throws DeliveryException {

		DeliveryDestinationAndCharge deliveryDestinationAndCharge = new DeliveryDestinationAndCharge(deliveryChargeDto);
		deliveryDestinationAndCharge.setDateTimeCreated(LocalDateTime.now());

		DeliveryDestinationAndCharge savedEntry = deliveryChargesRepository.saveAndFlush(deliveryDestinationAndCharge);

		if (savedEntry.getId() != 0L) {
			return true;
		}

		throw new DeliveryException(DeliveryException.ERROR_OCCURED_WHILE_SAVING_DELIVERY_DETAILS);
	}

	public boolean updateDeliveryDestinationAndCharge(long id, DeliveryDestinationAndChargeDto deliveryChargeDto)
			throws DeliveryException {

		Optional<DeliveryDestinationAndCharge> deliveryDestinationAndChargeOptional = deliveryChargesRepository
				.findById(id);

		if (deliveryDestinationAndChargeOptional.isPresent()) {
			DeliveryDestinationAndCharge deliveryDestinationAndCharge = deliveryDestinationAndChargeOptional.get();
			deliveryDestinationAndCharge.setDestination(deliveryChargeDto.getDestination());
			deliveryDestinationAndCharge.setDeliveryCharge(deliveryChargeDto.getDeliveryCharge());
			deliveryDestinationAndCharge.setDateTimeModified(LocalDateTime.now());

			deliveryChargesRepository.save(deliveryDestinationAndCharge);

			return true;

		}

		throw new DeliveryException(DeliveryException.ERROR_OCCURED_WHILE_SAVING_DELIVERY_DETAILS);

	}
}
