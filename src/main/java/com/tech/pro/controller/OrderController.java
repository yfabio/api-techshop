package com.tech.pro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.dto.OrderDto;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	
	/**
	 * Create order
	 * Private
	 */
	@PostMapping
	public ResponseEntity<OrderDto> createNewOrder(@RequestBody OrderDto orderDto){
		return ResponseEntity.ok(orderDto);		
	}
	
	
	/**
	 * Get logged in users order
	 * Private
	 */
	@GetMapping("/myorder/{id}")
	public ResponseEntity<List<OrderDto>> getMyOrders(@PathVariable String id){
		List<OrderDto> orders = new ArrayList<>();
		OrderDto orderDto = new OrderDto();
		orderDto.setId(id);
		orders.add(orderDto);
		return ResponseEntity.ok(orders);
	}
	
	
	
	
	/**
	 * Update order to paid
	 * Private
	 */
	@PutMapping("update/{id}")
	public ResponseEntity<OrderDto> updateOrderToPaid(@PathVariable String id, @RequestBody OrderDto orderDto){				
		return ResponseEntity.ok(orderDto);
	}
	
	
	
	
	// movie: 55:00
	
}
