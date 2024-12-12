package com.tech.pro.controller;

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
import com.tech.pro.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

	
	private OrderService orderService;
	
	/**
	 * Create order
	 * Private
	 */
	@PostMapping
	public ResponseEntity<OrderDto> addOrderItems(@RequestBody OrderDto orderDto){
		
		orderDto = orderService.addOrderItems(orderDto);
		
		return ResponseEntity.ok(orderDto);		
	}
	
	
	/**
	 * Get logged in users order
	 * Private
	 */
	@GetMapping("/myorder/{id}")
	public ResponseEntity<List<OrderDto>> getMyOrders(@PathVariable String id){		
		return ResponseEntity.ok(orderService.findOrderById(id));
	}
	
	
	
	/**
	 * Update order to paid
	 * 
	 * Verify if the user's name and email are present
	 * 
	 * Private
	 */
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable String id){		
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	
	
	
	/**
	 * Update order to paid
	 * Private
	 */
	@PutMapping("update/{id}")
	public ResponseEntity<OrderDto> updateOrderToPaid(@PathVariable String id, @RequestBody OrderDto orderDto){		
		return ResponseEntity.ok(orderDto);
	}
	
	
	
	
	
	
}
