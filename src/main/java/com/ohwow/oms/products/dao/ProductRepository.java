package com.ohwow.oms.products.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ohwow.oms.analytics.dto.ProductSalesProjection;
import com.ohwow.oms.products.domain.Product;
import com.ohwow.oms.products.dto.ProductAndInventoryDto;
import com.ohwow.oms.products.dto.ProductTransactionDto;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByOrderByIdDesc();

	List<Product> findByActiveTrue();

	Optional<Product> findByParentId(long parentId);

	@Query(value = "SELECT new com.ohwow.oms.products.dto.ProductAndInventoryDto(p.id, p.itemName, p.price, i.stockOnHand, i.committedStock, i.availableStock) FROM Product p INNER JOIN Inventory i ON i.productId = p.id")
	List<ProductAndInventoryDto> findAllProductWithInventory();

	@Query(value = "SELECT new com.ohwow.oms.products.dto.ProductTransactionDto(o.dateTimeCreated, o.id, c.firstName, c.lastName, od.quantity, p.price, od.total, o.orderStatus) FROM OrderDetail od INNER JOIN Product p ON p.id = od.product INNER JOIN Order o ON o.id = od.orderId INNER JOIN Customer c ON c.id = o.customer WHERE p.id = ?1")
	List<ProductTransactionDto> findAllTransactionByProductId(long id);

	@Query(value = "SELECT (select p2.item_name from products p2 where p.parent_id = p2.parent_id and active = true) AS productName, sum(od.quantity) AS amountSold FROM products p INNER JOIN order_details od ON od.product_id = p.id INNER JOIN orders o ON o.id = od.order_id WHERE o.order_status = 'COMPLETED' AND o.date_time_completed BETWEEN ?1 AND ?2 GROUP BY p.parent_id", nativeQuery = true)
	Collection<ProductSalesProjection> findAllProductSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
