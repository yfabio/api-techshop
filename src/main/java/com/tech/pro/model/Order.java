package com.tech.pro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
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
@Document(collection = "order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private User user;
	
	private List<OrderItem> orderItems;
	
	private ShippingAddress shippingAddress;

	private String paymentMethod;
	
	private PaymentResult paymentResult;
	
	private BigDecimal itemsPrice;
	
	private BigDecimal taxPrice;
	
	private BigDecimal shippingPrice;
	
	private BigDecimal totalPrice;
	
	private Boolean isPaid;
	
	private LocalDate paidAt;
	
	private Boolean isDelivered;
	
	private LocalDate deliveredAt;	
	
	@CreatedDate
	private LocalDateTime timestamps;
	
	
	

}
