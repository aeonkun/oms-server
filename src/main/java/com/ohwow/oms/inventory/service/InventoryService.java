package com.ohwow.oms.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohwow.oms.inventory.dao.InventoryRepository;
import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.inventory.exception.InventoryException;
import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto;
import com.ohwow.oms.inventoryadjustmenthistory.service.InventoryAdjustmentHistoryService;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryAdjustmentHistoryService inventoryAdjustmentHistoryService;

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
		int totalCommittedStock = inventory.getCommittedStock() + quantity;
		inventory.setCommittedStock(totalCommittedStock);
		inventory.setAvailableStock(inventory.getAvailableStock() - totalCommittedStock);
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

	public boolean updateInventory(long id, InventoryAdjustmentDto inventoryAdjustmentDto) throws InventoryException {
		Optional<Inventory> inventoryResult = inventoryRepository.findByProductId(id);

		if (inventoryResult.isPresent()) {
			Inventory inventory = inventoryResult.get();
			inventory.setStockOnHand(inventoryAdjustmentDto.getAdjustedStockOnHand());
			inventory
					.setAvailableStock(inventoryAdjustmentDto.getAdjustedStockOnHand() - inventory.getCommittedStock());
			inventoryRepository.save(inventory);
			inventoryAdjustmentHistoryService.addInventoryAdjustmentEntry(id, inventoryAdjustmentDto);

		} else
			throw new InventoryException(InventoryException.NO_EXISTING_INVENTORY_FOR_PRODUCT_EXCEPTION);

		return true;

	}

	private Inventory getInventoryById(long productId) throws InventoryException {
		Optional<Inventory> inventoryResult = inventoryRepository.findByProductId(productId);

		if (inventoryResult.isPresent()) {
			return inventoryResult.get();
		} else
			throw new InventoryException(InventoryException.NO_EXISTING_INVENTORY_FOR_PRODUCT_EXCEPTION);
	}
}
