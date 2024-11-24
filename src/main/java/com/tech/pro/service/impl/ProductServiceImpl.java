package com.tech.pro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.model.Product;
import com.tech.pro.repository.ProductRepository;
import com.tech.pro.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(String id) {
		return productRepository.findById(id);
	}
	
}
