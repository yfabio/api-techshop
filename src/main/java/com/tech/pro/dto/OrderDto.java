package com.tech.pro.dto;

import java.math.BigDecimal;
import java.util.List;

import com.tech.pro.model.OrderItem;
import com.tech.pro.model.ShippingAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private List<OrderItem> orderItems;	
	private ShippingAddress shippingAddress;
	private String paymentMethod;	
	private BigDecimal itemsPrice;	
	private BigDecimal taxPrice;	
	private BigDecimal shippingPrice;	
	private BigDecimal totalPrice;
	
	
}
