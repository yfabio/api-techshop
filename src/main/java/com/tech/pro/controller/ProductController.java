package com.tech.pro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.exception.ResourceNotFoundException;
import com.tech.pro.model.Product;
import com.tech.pro.service.ImageService;
import com.tech.pro.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
	
	private ProductService productService;
		
	private ImageService imageService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {		
		return ResponseEntity.ok(productService.getProducts());					
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		return ResponseEntity.ok(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found!")));					
	}
	
	
	@GetMapping(value = "/img/{imgName}")
	public byte[] loadImage(@PathVariable String imgName){		
		return imageService.getImage(imgName);
	}

}
