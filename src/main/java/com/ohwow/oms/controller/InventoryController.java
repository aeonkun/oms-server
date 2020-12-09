package com.ohwow.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.inventory.exception.InventoryException;
import com.ohwow.oms.inventory.service.InventoryService;
import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto;
import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentHistoryResponse;
import com.ohwow.oms.inventoryadjustmenthistory.service.InventoryAdjustmentHistoryService;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@Autowired
	InventoryAdjustmentHistoryService inventoryAdjustmentHistoryService;

	@GetMapping("/inventories")
	public List<Inventory> getAllInventories() {
		return inventoryService.getAllInventories();
	}

	@GetMapping("/inventories/{id}")
	public List<Inventory> updateProductInventory(@PathVariable long id, int newStock) {
		return inventoryService.getAllInventories();
	}

	@PostMapping("/inventories/{id}/adjustments/create")
	public boolean adjustInventory(@PathVariable long id, @RequestBody InventoryAdjustmentDto inventoryAdjustmentDto)
			throws InventoryException {
		return inventoryService.updateInventory(id, inventoryAdjustmentDto);
	}

	@GetMapping("/inventories/{id}/adjustments")
	public InventoryAdjustmentHistoryResponse getInventoryAdjustmentsById(@PathVariable long id, @RequestParam int page,
			@RequestParam int rows) {
		return inventoryAdjustmentHistoryService.getInventoryAdjustmentsById(page, rows, id);
	}

	@GetMapping("/inventories/adjustments")
	public InventoryAdjustmentHistoryResponse getInventoryAdjustments(@RequestParam int page, @RequestParam int rows) {
		return inventoryAdjustmentHistoryService.getInventoryAdjustments(page, rows);
	}

}
