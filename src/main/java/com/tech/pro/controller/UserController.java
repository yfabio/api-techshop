package com.tech.pro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.model.User;
import com.tech.pro.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
	
	
	/**
	 * Get user profile
	 * @param id
	 * 	 
	 * PRIVATE 
	 * @return
	 */
	@GetMapping("/profile/{id}")
	public ResponseEntity<User> getUserProfile(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	
	/**
	 * Update user profile
	 * PRIVATE
	 * @param id	
	 * @return
	 */
	@PutMapping("/profile/{id}")
	public ResponseEntity<User> updateUserProfile(@PathVariable String id, @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(id,user));
	}
		
	
}
