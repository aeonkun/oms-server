package com.ohwow.oms.producthistory.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohwow.oms.producthistory.dao.ProductHistoryRepository;
import com.ohwow.oms.producthistory.domain.ProductHistory;
import com.ohwow.oms.products.domain.Product;
import com.ohwow.oms.products.dto.ProductDto;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductHistoryService {

	private static final String ITEM_NAME = "item name";
	private static final String PRICE = "price";

	@Autowired
	ProductHistoryRepository productHistoryRepository;

	public List<ProductHistory> getAllProductHistory() {

		return productHistoryRepository.findAll();
	}

	public boolean createProductHistory(Product product, ProductDto newProduct) {

		boolean isSuccessful = false;

		if (!product.getItemName().equals(newProduct.getItemName())) {
			ProductHistory productHistory = createProductHistoryForUpdate(product.getId(), ITEM_NAME,
					product.getItemName(), newProduct.getItemName(), newProduct.getModifiedBy());
			productHistoryRepository.save(productHistory);
			isSuccessful = true;
		}

		if (product.getPrice() != newProduct.getPrice()) {
			ProductHistory productHistory = createProductHistoryForUpdate(product.getId(), PRICE,
					String.valueOf(product.getPrice()).split("\\.")[0],
					String.valueOf(newProduct.getPrice()).split("\\.")[0], newProduct.getModifiedBy());
			productHistoryRepository.save(productHistory);
			isSuccessful = true;
		}

		return isSuccessful;
	}

	public ProductHistory createProductHistoryForUpdate(long productId, String changeType, String oldValue,
			String newValue, String modifiedBy) {

		return new ProductHistory(productId, changeType, oldValue, newValue, modifiedBy, LocalDateTime.now());
	}

	public List<ProductHistory> getProductHistory(long id) {
		return productHistoryRepository.findByProductIdOrderByDateTimeModifiedDesc(id);
	}
}
