package com.tech.pro.model;

import java.io.Serializable;

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
public class ShippingAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;
			
	private String address;
	
	private String city;
	
	private String postalCode;
	
	private String country;
	
	
}
