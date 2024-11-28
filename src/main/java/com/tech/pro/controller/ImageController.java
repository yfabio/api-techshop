package com.tech.pro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.service.ImageService;

@RestController
@RequestMapping("/api/products")
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	@GetMapping(value = "/img/{imgName}")
	public byte[] loadImage(@PathVariable String imgName){		
		return imageService.getImage(imgName);
	}
	
}
