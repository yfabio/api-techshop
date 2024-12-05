package com.tech.pro.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tech.pro.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
	
	
}	
