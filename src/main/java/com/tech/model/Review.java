package com.tech.model;

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
@Document(collection = "review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private User user;
	
	private String name;
	
	private Integer rating;
	
	private String comment;
		
	@CreatedDate
	private LocalDateTime timestamps;
	
	
}
