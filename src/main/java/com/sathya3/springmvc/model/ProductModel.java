package com.sathya3.springmvc.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel {
	
	 @NotBlank(message="name cannot be blank")
	private String name;
	 
	 @NotBlank(message="brand cannot be blank")
		private String brand;
	 
	 @NotBlank(message=" madeIn  field cannot be blank")
		private String madeIn;
	 
	 @Positive(message="price must be greater than 0")
		private double price;
	 
	 @Min(value=1, message="Quantity must be atlest 1")
		private double  quantity ;
	 
	@DecimalMax(value="100.0", message="Discount rate cannnot exceed 100")
	private double discountRate;
	
 
	 
	 
}