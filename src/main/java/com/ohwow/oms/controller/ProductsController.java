package com.ohwow.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohwow.oms.producthistory.domain.ProductHistory;
import com.ohwow.oms.producthistory.service.ProductHistoryService;
import com.ohwow.oms.products.dto.ProductAndInventoryDto;
import com.ohwow.oms.products.dto.ProductDto;
import com.ohwow.oms.products.dto.ProductTransactionDto;
import com.ohwow.oms.products.exception.ProductException;
import com.ohwow.oms.products.service.ProductService;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin
public class ProductsController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductHistoryService productHistoryService;

	@GetMapping("/products")
	public List<ProductAndInventoryDto> getAllProducts() {

		return productService.getProducts();
	}

	@PostMapping("/products")
	public boolean createProductWithInventory(@RequestBody ProductDto product) {
		return productService.createProductWithInventory(product);
	}

	@GetMapping("/producthistories")
	public List<ProductHistory> getHistories() {
		return productHistoryService.getAllProductHistory();
	}

	@GetMapping("/products/{id}")
	public ProductDto getProductById(@PathVariable long id) throws ProductException {
		return productService.getProductById(id);
	}

	@PutMapping("/products/{id}")
	public boolean updateProduct(@PathVariable long id, @RequestBody ProductDto product) {
		return productService.updateProduct(id, product);
	}

	@GetMapping("/products/{id}/history")
	public List<ProductHistory> getProductHistories(@PathVariable long id) {
		return productHistoryService.getProductHistory(id);
	}

	@GetMapping("/products/{id}/transactions")
	public List<ProductTransactionDto> getAllTransactionByProductId(@PathVariable long id) {
		return productService.getAllTransactionByProductId(id);
	}
}
