package com.sathya3.springmvc.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductEntity {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String brand;
	private String madeIn;
	private double price;
	private double Quantity;
	private double discountRate;
   private double taxprice;
	
   private double discountprice;
	private double offerprice;
	private double finalprice;
	private double stockvalue;
	
		
	
}
