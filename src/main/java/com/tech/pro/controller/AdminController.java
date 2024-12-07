package com.tech.pro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

	private UserService userService;

	
	/**
	 * get users
	 * private
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	/**
	 * delete user
	 * private
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	/**
	 * get user by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	
	/**
	 * update user
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/profile/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(id,user));
	}
	

	

	
}
