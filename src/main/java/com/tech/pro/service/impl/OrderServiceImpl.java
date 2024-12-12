package com.tech.pro.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tech.pro.dto.OrderDto;
import com.tech.pro.exception.ApiException;
import com.tech.pro.model.Order;
import com.tech.pro.repository.OrderRepository;
import com.tech.pro.service.OrderService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	
	private OrderRepository orderRepository;
	
	private ModelMapper modelMapper;
	
	
	@Override
	public OrderDto addOrderItems(OrderDto orderDto) {
		
		if(orderDto.getOrderItems() == null || orderDto.getOrderItems().size() == 0) {
			throw new ApiException("No order items");
		}else {
			
			Order order = modelMapper.map(orderDto, Order.class);	
			
			Order savedOrder = orderRepository.save(order);
					
			return modelMapper.map(savedOrder, OrderDto.class);
		}
		
		
	}


	@Override
	public List<OrderDto> findOrderById(String id) {
		
		if(!orderRepository.existsById(id)) {
			throw new ApiException("Order not found");
		}
		
		List<OrderDto> ordersDto = orderRepository.findOrdersByUserId(id)
					   .stream()
					   .map(order -> modelMapper.map(order, OrderDto.class))
					   .collect(Collectors.toList());
		
		return ordersDto;
	}


	@Override
	public OrderDto getOrderById(String id) {
		
		Order order = orderRepository.findById(id).orElseThrow(() -> new ApiException("Order not found"));
				
		return modelMapper.map(order, OrderDto.class);
	}



	
	
	
	
	
}
