package com.ohwow.oms.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.inventory.dao.InventoryRepository;
import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.inventory.exception.InventoryException;
import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto;
import com.ohwow.oms.inventoryadjustmenthistory.service.InventoryAdjustmentHistoryService;
import com.ohwow.oms.order.OrderStatusEnum;
import com.ohwow.oms.order.dao.OrderRepository;
import com.ohwow.oms.order.domain.Order;
import com.ohwow.oms.order.service.OrderService;
import com.ohwow.oms.orderdetails.dao.OrderDetailRepository;
import com.ohwow.oms.orderdetails.domain.OrderDetail;
import com.ohwow.oms.orderdetails.service.OrderDetailService;
import com.ohwow.oms.products.domain.Product;
import com.ohwow.oms.products.exception.ProductException;
import com.ohwow.oms.products.service.ProductService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryAdjustmentHistoryService inventoryAdjustmentHistoryService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	ProductService productService;

	/**
	 * Create inventory for product
	 * 
	 * @param inventory
	 * @return
	 */
	public Inventory createInventory(Inventory inventory) {

		return inventoryRepository.save(inventory);
	}

	/**
	 * Fetch all inventories from database
	 * 
	 * @return list of Inventory
	 */
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	public boolean commitStock(long productId, int quantity) throws InventoryException {

		Inventory inventory = getInventoryById(productId);
		inventory.setCommittedStock(inventory.getCommittedStock() + quantity);
		inventory.setAvailableStock(inventory.getAvailableStock() - quantity);
		inventoryRepository.save(inventory);

		return true;
	}

	public boolean returnCommittedStock(long productId, int quantity) throws InventoryException {

		Inventory inventory = getInventoryById(productId);
		inventory.setCommittedStock(inventory.getCommittedStock() - quantity);
		inventory.setAvailableStock(inventory.getAvailableStock() + quantity);
		inventoryRepository.save(inventory);

		return true;

	}

	public boolean deductFromStockOnHand(long productId, int quantity) throws InventoryException {

		Inventory inventory = getInventoryById(productId);
		inventory.setCommittedStock(inventory.getCommittedStock() - quantity);
		inventory.setStockOnHand(inventory.getStockOnHand() - quantity);
		inventoryRepository.save(inventory);

		return true;

	}

	public boolean updateInventory(long id, InventoryAdjustmentDto inventoryAdjustmentDto)
			throws InventoryException, ProductException {

		Product product = productService.getProductById(id);
		Optional<Inventory> inventoryResult = inventoryRepository.findByProductId(product.getParentId());

		if (inventoryResult.isPresent()) {
			Inventory inventory = inventoryResult.get();
			inventory.setStockOnHand(inventoryAdjustmentDto.getAdjustedStockOnHand());
			inventory
					.setAvailableStock(inventoryAdjustmentDto.getAdjustedStockOnHand() - inventory.getCommittedStock());
			inventoryRepository.save(inventory);
			inventoryAdjustmentHistoryService.addInventoryAdjustmentEntry(id, inventoryAdjustmentDto);
			evaluateAndUpdateInventoryIssuesForAllNewOrders();

		} else
			throw new InventoryException(InventoryException.NO_EXISTING_INVENTORY_FOR_PRODUCT_EXCEPTION);

		return true;

	}

	/**
	 * Evaluates all order with the status of "NEW" to know if the stock is not
	 * empty. Updates the order and order detail object based on the stock levels.
	 * 
	 * @throws InventoryException
	 */
	public void evaluateAndUpdateInventoryIssuesForAllNewOrders() throws InventoryException {
		List<Order> newOrders = orderRepository.findAllByOrderStatus(OrderStatusEnum.NEW);

		for (Order order : newOrders) {
			boolean hasStockIssues = false;
			List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order);

			System.out.println("order details");

			for (OrderDetail orderDetail : orderDetails) {
				System.out.println("product id: " + orderDetail.getProduct().getId());
				System.out.println("parent: " + orderDetail.getProduct().getParentId());
			}

			for (OrderDetail orderDetail : orderDetails) {

				System.out.println("id: " + orderDetail.getProduct().getId() + " parent id: "
						+ orderDetail.getProduct().getParentId());

				boolean isInventoryEnough = checkIfInventoryIsEnough(orderDetail.getProduct().getParentId());
				orderDetail.setHasEnoughStock(isInventoryEnough);
				orderDetailRepository.save(orderDetail);

				if (!isInventoryEnough) {
					hasStockIssues = true;
				}
			}

			order.setHasStockIssues(hasStockIssues);
			orderRepository.saveAndFlush(order);
		}
	}

	public Inventory getInventoryById(long productId) throws InventoryException {
		Optional<Inventory> inventoryResult = inventoryRepository.findByProductId(productId);

		if (inventoryResult.isPresent()) {
			return inventoryResult.get();
		} else
			throw new InventoryException(InventoryException.NO_EXISTING_INVENTORY_FOR_PRODUCT_EXCEPTION);
	}

	private boolean checkIfInventoryIsEnough(long productId) throws InventoryException {

		Inventory inventory = getInventoryById(productId);

		return inventory.getAvailableStock() >= 0;

	}
}
