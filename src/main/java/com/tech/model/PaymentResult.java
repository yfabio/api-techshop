package com.tech.model;

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
public class PaymentResult implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	private String id;
	
	private String status;
	
	private String updateTime;
	
	private String emailAddress;
}
