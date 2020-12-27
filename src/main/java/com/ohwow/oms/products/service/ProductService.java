package com.ohwow.oms.products.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.inventory.domain.Inventory;
import com.ohwow.oms.inventory.exception.InventoryException;
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
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
	 * @throws InventoryException
	 */
	public List<ProductAndInventoryDto> getProducts() throws InventoryException {

		List<Product> allProducts = productRepository.findAll();

		// get all parent products
		List<Product> parentProducts = allProducts.stream().filter(p -> p.getId() == p.getParentId())
				.collect(Collectors.toList());

		// get latest version per parent
		List<Product> latestVersionsOfProducts = getLatestVersionsOfParentProducts(allProducts, parentProducts);

		List<ProductAndInventoryDto> productAndInventoryDtos = new ArrayList<>();

		// get inventory of each product
		for (Product product : latestVersionsOfProducts) {
			Inventory inventory = inventoryService.getInventoryById(product.getParentId());

			ProductAndInventoryDto productAndInventoryDto = new ProductAndInventoryDto(product.getId(),
					product.getItemName(), product.getPrice(), inventory.getStockOnHand(),
					inventory.getCommittedStock(), inventory.getAvailableStock());

			productAndInventoryDtos.add(productAndInventoryDto);
		}

		return productAndInventoryDtos;
	}

	private List<Product> getLatestVersionsOfParentProducts(List<Product> allProducts, List<Product> parentProducts) {
		List<Product> latestProductVersions = new ArrayList<>();

		for (Product parentProduct : parentProducts) {
			List<Product> versions = allProducts.stream().filter(p -> p.getParentId() == parentProduct.getId())
					.collect(Collectors.toList());

			Product latestVersion = versions.stream().max(Comparator.comparing(p -> p.getId())).get();
			latestProductVersions.add(latestVersion);
		}

		return latestProductVersions;
	}

	/**
	 * Fetch product based on the supplied id
	 * 
	 * @param id
	 * @return
	 * @throws ProductException
	 * @throws InventoryException
	 */
	public ProductDto getProductAndInventoryById(long id) throws ProductException, InventoryException {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			Inventory inventory = inventoryService.getInventoryById(product.getParentId());
			return new ProductDto(product, inventory.getStockOnHand());
		} else
			throw new ProductException(ProductException.PRODUCT_NOT_FOUND_EXCEPTION);
	}

	/**
	 * Retrieve product base on the supplied id
	 * 
	 * @param id
	 * @return
	 * @throws ProductException
	 */
	public Product getProductById(long id) throws ProductException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
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
			product.setActive(true);
			product.setDateTimeCreated(LocalDateTime.now());
			Product savedProduct = productRepository.saveAndFlush(product);
			product.setParentId(savedProduct.getId());
			productRepository.save(product);
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
	 * @throws InventoryException
	 */
	public boolean updateProduct(long id, ProductDto productDto) throws InventoryException {

		boolean isSuccessful = false;

		if (!Objects.isNull(productDto)) {

			Optional<Product> optionalProduct = productRepository.findById(id);

			if (optionalProduct.isPresent()) {

				Product product = optionalProduct.get();
				productHistoryService.createProductHistory(product, productDto);

				// Create new version for the product
				Product newVersion = new Product();
				newVersion.setItemName(productDto.getItemName());
				newVersion.setPrice(productDto.getPrice());
				newVersion.setCreatedBy(product.getCreatedBy());
				newVersion.setDateTimeCreated(product.getDateTimeCreated());
				newVersion.setModifiedBy(productDto.getModifiedBy());
				newVersion.setDateTimeModified(LocalDateTime.now());
				newVersion.setActive(true);
				newVersion.setParentId(product.getParentId());

				productRepository.save(newVersion);

				// deactivate previous version
				product.setActive(false);
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
	 * @throws ProductException
	 */
	public List<ProductTransactionDto> getAllTransactionByProductId(long id) throws ProductException {

		Optional<Product> optionalProduct = productRepository.findById(id);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();

			return productRepository.findAllTransactionByProductId(product.getParentId());
		}

		throw new ProductException(ProductException.PRODUCT_NOT_FOUND_EXCEPTION);
	}
}
