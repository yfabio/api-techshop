package com.tech.pro.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.pro.exception.ApiException;
import com.tech.pro.model.User;
import com.tech.pro.repository.UserRepository;
import com.tech.pro.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
		
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) {		
		return userRepository.findById(id).orElseThrow(() -> new ApiException("User not found!"));
	}

	@Override
	public String deleteUser(String id) {		
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}else {
			throw new ApiException("User not found");
		}
		return "User has been deleted!";
	}

	@Override
	public User registerUser(User user) {
		String pwdHashed = passwordEncoder.encode(user.getPassword());
		user.setPassword(pwdHashed);
		return userRepository.save(user);		
	}

	@Override
	public User findByEmail(String email) {		
		return userRepository.findByEmail(email).orElseThrow(() -> new ApiException("User not found"));
	}

	@Override
	public boolean verify(String pwd, String hashPassword) {		
		return passwordEncoder.matches(pwd, hashPassword);
	}

	@Override
	public User updateUser(String id, User user) {		
		if(userRepository.existsById(id)) {
			user.setId(id);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}else {
			throw new ApiException("User not found!");
		}		
	}

	@Override
	public boolean existUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	
	
	
	
}
