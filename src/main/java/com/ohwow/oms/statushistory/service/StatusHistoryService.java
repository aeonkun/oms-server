package com.ohwow.oms.statushistory.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.order.exception.OrderException;
import com.ohwow.oms.order.service.OrderService;
import com.ohwow.oms.statushistory.dao.StatusHistoryRepository;
import com.ohwow.oms.statushistory.domain.StatusHistory;
import com.ohwow.oms.statushistory.dto.OrderStatusAndStatusHistoryDto;
import com.ohwow.oms.statushistory.dto.StatusHistoryDto;

@Service
public class StatusHistoryService {

	@Autowired
	StatusHistoryRepository statusHistoryRepository;

	@Autowired
	OrderService orderService;

	public OrderStatusAndStatusHistoryDto getOrderStatusAndHistory(long id) throws OrderException {

		Optional<List<StatusHistory>> statusHistoriesResult = statusHistoryRepository.findByOrderIdOrderByIdDesc(id);
		OrderStatusAndStatusHistoryDto orderStatusAndStatusHistoryDto;

		if (statusHistoriesResult.isPresent()) {

			List<StatusHistoryDto> statusAndHistories = statusHistoriesResult.get().stream()
					.map(x -> new StatusHistoryDto(id, x.getOldStatus(), x.getNewStatus(), x.getModifiedBy(),
							x.getDateTimeModified()))
					.collect(Collectors.toList());
			orderStatusAndStatusHistoryDto = new OrderStatusAndStatusHistoryDto(orderService.getOrderStatusById(id),
					statusAndHistories);

		} else {

			orderStatusAndStatusHistoryDto = new OrderStatusAndStatusHistoryDto(orderService.getOrderStatusById(id),
					new ArrayList<>());
		}

		return orderStatusAndStatusHistoryDto;
	}

	/**
	 * update the status and history and add the new entry
	 * 
	 * @param order
	 * @param orderStatus
	 * @param user
	 * @return
	 */
	public boolean updateStatusAndHistory(Order order, OrderStatusEnum orderStatus, String user) {

		boolean isSuccessful = false;
		OrderStatusEnum oldStatus = order.getOrderStatus();
		order.setOrderStatus(orderStatus);

		if (addEntryToStatusHistory(order, oldStatus, orderStatus, user)) {
			isSuccessful = true;
		}

		return isSuccessful;

	}

	/**
	 * Add new entry to status history
	 * 
	 * @param order
	 * @param oldStatus
	 * @param newStatus
	 * @param user
	 * @return
	 */
	private boolean addEntryToStatusHistory(Order order, OrderStatusEnum oldStatus, OrderStatusEnum newStatus,
			String user) {

		StatusHistory statusHistory = new StatusHistory();
		statusHistory.setDateTimeModified(LocalDateTime.now());
		statusHistory.setModifiedBy(user);
		statusHistory.setNewStatus(newStatus);
		statusHistory.setOldStatus(oldStatus);
		statusHistory.setOrder(order);

		if (!ObjectUtils.isEmpty(statusHistoryRepository.save(statusHistory))) {
			return true;
		}

		return false;
	}

}
