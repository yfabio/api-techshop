package com.tech.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
