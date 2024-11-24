package com.tech.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.model.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>{

}
