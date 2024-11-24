package com.tech.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Document(collection = "product")
public class Product implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
		
    private String name;
    
    private String image;
    
    private String brand;
    
    private String category;
    
    private String description;
    
    private Integer rating;
    
    private Integer numReviews;
    
    private BigDecimal price;
    
    private Integer countInStock;
    
    @CreatedDate
    private LocalDateTime timestamps;
    
    private User user;
    
    private List<Review> reviews;

}
