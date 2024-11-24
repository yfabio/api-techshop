package com.tech.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.Product;
import com.tech.pro.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {		
		return ResponseEntity.ok(productService.getProducts());					
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		return ResponseEntity.ok(productService.findById(id).orElseThrow(() -> new RuntimeException("resource not found!")));
					
	}
	

}
