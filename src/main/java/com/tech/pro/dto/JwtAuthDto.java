package com.tech.pro.dto;

import lombok.Data;

@Data
public class JwtAuthDto {

	private String token;
	private String tokenType = "Bearer";
	
	public JwtAuthDto(String token) {
		this.token = token;
	}

	
	
}
