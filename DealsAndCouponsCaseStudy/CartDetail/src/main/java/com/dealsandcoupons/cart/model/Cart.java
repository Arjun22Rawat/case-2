package com.dealsandcoupons.cart.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart
{
	@Id
	@NotBlank(message = "Customer ID is required")
	private String customerId;
	@NotBlank(message = "Item ID is required")
	private String itemId;
	  @NotNull(message = "Quantity is required")
	    @PositiveOrZero(message = "Quantity must be a positive integer or zero")
	private int quantity;
	private HashMap<String,Integer> itemDetails;
	  @PositiveOrZero(message = "Cart total must be a positive number or zero")
	private double cartTotal;
}
