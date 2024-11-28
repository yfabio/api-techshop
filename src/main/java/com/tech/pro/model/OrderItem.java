package com.tech.pro.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document
public class OrderItem {
	
	private String name;
	
	private Integer qty;
	
	private String image;
	
	private BigDecimal price;
	
	private Product product;
	
	
}
