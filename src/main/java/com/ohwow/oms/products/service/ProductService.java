package com.ohwow.oms.products.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.inventory.service.InventoryService;
import com.ohwow.oms.producthistory.service.ProductHistoryService;
import com.ohwow.oms.products.dao.ProductRepository;
import com.ohwow.oms.products.domain.Product;
import com.ohwow.oms.products.dto.ProductAndInventoryDto;
import com.ohwow.oms.products.dto.ProductDto;
import com.ohwow.oms.products.dto.ProductTransactionDto;
import com.ohwow.oms.products.exception.ProductException;

/**
 * @author carlo
 *
 */
@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductHistoryService productHistoryService;

	/**
	 * Fetch all products from the database
	 * 
	 * @return list of products
	 */
	public List<ProductAndInventoryDto> getProducts() {

		return productRepository.findAllProductWithInventory();
	}

	/**
	 * Fetch product based on the supplied id
	 * 
	 * @param id
	 * @return
	 * @throws ProductException
	 */
	public ProductDto getProductById(long id) throws ProductException {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			return new ProductDto(product.get());
		} else
			throw new ProductException(ProductException.PRODUCT_NOT_FOUND_EXCEPTION);
	}

	/**
	 * Create product with the corresponding inventory
	 * 
	 * @return boolean isSuccessful
	 */
	public boolean createProductWithInventory(ProductDto productDto) {

		boolean isSuccessful = false;

		if (!Objects.isNull(productDto)) {
			Product product = new Product(productDto);
			product.setDateTimeCreated(LocalDateTime.now());
			Product savedProduct = productRepository.save(product);
			isSuccessful = true;
			Inventory inventory = new Inventory(savedProduct.getId(), productDto.getStockOnHand(), 0,
					productDto.getStockOnHand());
			inventoryService.createInventory(inventory);
		}

		return isSuccessful;
	}

	/**
	 * Update product details (item name and/or price)
	 * 
	 * @return boolean isSuccessful
	 */
	public boolean updateProduct(long id, ProductDto productDto) {

		boolean isSuccessful = false;

		if (!Objects.isNull(productDto)) {

			Optional<Product> optionalProduct = productRepository.findById(id);

			if (optionalProduct.isPresent()) {
				Product product = optionalProduct.get();
				productHistoryService.createProductHistory(product, productDto);
				product.setDateTimeModified(LocalDateTime.now());
				product.setModifiedBy(productDto.getModifiedBy());
				product.setItemName(productDto.getItemName());
				product.setPrice(productDto.getPrice());
				productRepository.save(product);
				isSuccessful = true;
			}
		}

		return isSuccessful;
	}

	/**
	 * Get all transactions for the product with the supplied id
	 * 
	 * @param id
	 * @return
	 */
	public List<ProductTransactionDto> getAllTransactionByProductId(long id) {
		return productRepository.findAllTransactionByProductId(id);
	}
}
