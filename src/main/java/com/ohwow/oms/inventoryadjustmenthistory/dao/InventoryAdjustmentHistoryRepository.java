package com.ohwow.oms.inventoryadjustmenthistory.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.inventoryadjustmenthistory.domain.InventoryAdjustmentHistory;
import com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto;

@Repository
public interface InventoryAdjustmentHistoryRepository extends JpaRepository<InventoryAdjustmentHistory, Long> {

	List<InventoryAdjustmentHistory> findAllByProductId(long id);

	List<InventoryAdjustmentHistory> findAllByOrderByDateTimeAdjustedDesc();

	@Query(value = "SELECT new com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto(p, iah.adjustedStockOnHand, iah.adjustedBy, iah.dateTimeAdjusted, iah.notes) FROM InventoryAdjustmentHistory iah INNER JOIN Product p ON p.id = iah.productId ORDER BY iah.dateTimeAdjusted DESC")
	Page<InventoryAdjustmentDto> findAllInventoryAdjustments(Pageable pageable);

	@Query(value = "SELECT new com.ohwow.oms.inventoryadjustmenthistory.dto.InventoryAdjustmentDto(p, iah.adjustedStockOnHand, iah.adjustedBy, iah.dateTimeAdjusted, iah.notes) FROM InventoryAdjustmentHistory iah INNER JOIN Product p ON p.id = iah.productId WHERE iah.productId = ?1")
	Page<InventoryAdjustmentDto> findAllInventoryAdjustmentsByProductId(Pageable pageable, long id);

}
