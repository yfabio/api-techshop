package com.tech.pro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.dto.JwtAuthDto;
import com.tech.pro.dto.SingInDto;
import com.tech.pro.dto.SingUpDto;
import com.tech.pro.model.User;
import com.tech.pro.security.JwtTokenProvider;
import com.tech.pro.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private UserService userService;
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	
	/**
	 * Auth user & get token
	 * route /api/users/signin
	 * PUBLIC 
	 * @param signUpDto
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthDto> singIn(@RequestBody SingInDto signInDto){
		
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token = jwtTokenProvider.generateToken(auth);
		
		User user = User.class.cast(auth.getPrincipal());
		
		return ResponseEntity.ok(new JwtAuthDto(token,user));
	}
	
	/**
	 * Register user
	 * @param signUpDto
	 * PUBLIC
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody SingUpDto signUpDto){
		
		if(userService.existUserByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email was already taken",HttpStatus.BAD_REQUEST);			
		}
		
		User user = new User();
		user.setEmail(signUpDto.getEmail());
		user.setPassword(signUpDto.getPassword());
		user.setName(signUpDto.getName());
				
		return ResponseEntity.ok(userService.registerUser(user));
	}
	
}
