package com.tech.pro.service;

import java.util.List;
import java.util.Optional;

import com.tech.pro.model.Product;

public interface ProductService {
	
	List<Product> getProducts();

	Optional<Product> findById(String id);
	
}
