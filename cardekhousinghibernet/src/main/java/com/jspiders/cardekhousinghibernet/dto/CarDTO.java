package com.jspiders.cardekhousinghibernet.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CarDTO {
	
	@Id
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String colour;
	@Column(nullable = false)
	private String fuleType;
	@Column(nullable = false)
	private double price;
	
	
}
