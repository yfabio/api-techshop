package com.tech.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.pro.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
