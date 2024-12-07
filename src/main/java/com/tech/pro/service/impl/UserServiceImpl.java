package com.tech.pro.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.pro.exception.ApiException;
import com.tech.pro.model.User;
import com.tech.pro.repository.UserRepository;
import com.tech.pro.security.Role;
import com.tech.pro.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
		
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {		
		return userRepository.findAll().stream().filter(user -> !user.isAdmin()).toList();
	}

	@Override
	public User getUserById(String id) {	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = User.class.cast(auth.getPrincipal());
				
		if(!user.getId().equals(id) && user.getRole().equals(Role.USER)) {
			throw new ApiException("Not logged user id");
		}
		
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
	public User updateUser(String id, User user) {		
		if(userRepository.existsById(id)) {
			
			User userDb = userRepository.findById(id).get();
			
			userDb.setName(user.getName());
			userDb.setEmail(user.getEmail());
			
			if(user.getPassword()!=null) {
				userDb.setPassword(passwordEncoder.encode(user.getPassword()));
			}			
			
			return userRepository.save(userDb);
		}else {
			throw new ApiException("User not found!");
		}		
	}

	@Override
	public boolean existUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	
	
	
	
}
