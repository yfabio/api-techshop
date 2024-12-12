package com.tech.pro.service;

import java.util.List;

import com.tech.pro.dto.OrderDto;

public interface OrderService {
	
	
	List<OrderDto> findOrderById(String id);

	OrderDto addOrderItems(OrderDto orderDto);

	OrderDto getOrderById(String id);
	
}
