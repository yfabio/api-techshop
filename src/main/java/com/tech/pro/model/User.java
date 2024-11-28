package com.tech.pro.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Document(collection = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Boolean isAdmin;
	
	@CreatedDate
	private LocalDateTime timestamps;
}