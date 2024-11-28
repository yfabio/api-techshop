package com.tech.pro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ImageController {

	@GetMapping(value = "/img")
	public ResponseEntity<Byte[]> loadImage(){
		
		
		return null;
	}
	
}
