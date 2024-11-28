package com.tech.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.pro.model.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

}
