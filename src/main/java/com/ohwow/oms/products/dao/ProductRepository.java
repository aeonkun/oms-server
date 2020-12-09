package com.ohwow.oms.products.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.products.domain.Product;
import com.ohwow.oms.products.dto.ProductAndInventoryDto;
import com.ohwow.oms.products.dto.ProductTransactionDto;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByOrderByIdDesc();

	@Query(value = "SELECT new com.ohwow.oms.products.dto.ProductAndInventoryDto(p.id, p.itemName, p.price, i.stockOnHand, i.committedStock, i.availableStock) FROM Product p INNER JOIN Inventory i ON i.productId = p.id")
	List<ProductAndInventoryDto> findAllProductWithInventory();

	@Query(value = "SELECT new com.ohwow.oms.products.dto.ProductTransactionDto(o.dateTimeCreated, o.id, c.firstName, c.lastName, od.quantity, p.price, od.total, o.orderStatus) FROM OrderDetail od INNER JOIN Product p ON p.id = od.product INNER JOIN Order o ON o.id = od.orderId INNER JOIN Customer c ON c.id = o.customer WHERE p.id = ?1")
	List<ProductTransactionDto> findAllTransactionByProductId(long id);
}
