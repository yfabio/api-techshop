package com.tech.pro.dto;

import com.tech.pro.model.User;

import lombok.Data;

@Data
public class JwtAuthDto {

	private String token;
	
	private String name;
	
	private String email;
	
	private String id;
	
	public JwtAuthDto(String token, User user) {
		this.token = token;
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();		
	}	
}
