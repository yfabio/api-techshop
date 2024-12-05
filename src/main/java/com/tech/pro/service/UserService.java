package com.tech.pro.service;

import java.util.List;

import com.tech.pro.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(String id);
	
	String deleteUser(String id);
	
	User registerUser(User user);
	
	User findByEmail(String email);
	
	boolean verify(String pwd, String hashPassword);

	User updateUser(String id, User user);

	boolean existUserByEmail(String email);
}
