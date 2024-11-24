package com.tech.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.service.DBLoader;

@RestController
@RequestMapping("/api/techshop")
public class SeederController {
	
	
	@Autowired
	private DBLoader dbLoader;
	
	
	@GetMapping("/import")
	public String importData() {
		dbLoader.importData();
		return "data has been imported";
		
	}
	
	
	@GetMapping("/destroy")
	public String destroyData() {
		dbLoader.destroyData();
		return "data has been destroyed";
		
	}

}
