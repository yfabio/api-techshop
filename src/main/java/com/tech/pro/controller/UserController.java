package com.tech.pro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/profile/{id}")
	public ResponseEntity<User> getUserProfile(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	
	
}
