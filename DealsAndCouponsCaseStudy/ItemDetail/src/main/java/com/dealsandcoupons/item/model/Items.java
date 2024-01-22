package com.dealsandcoupons.item.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="items")
public class Items 
{
	@Id
	@NotBlank(message = "Item ID is required")
	private String itemId;
	  @NotBlank(message = "Item Name is required")
	private String itemName;
	 @NotNull(message = "Price is required")
	    @PositiveOrZero(message = "Price must be a positive number or zero")
	private double price;
	 
	 
	 
	private String couponCode;
	 @PositiveOrZero(message = "Coupon discount must be a positive number or zero")
	private int couponDiscount;
	private byte[] image;
	 @PositiveOrZero(message = "Rating must be a positive number or zero")
	private int rating;
	private String description;
	  @PositiveOrZero(message = "Total available quantity must be a positive number or zero")
	private int totalAvailableQuantity;
	 @PositiveOrZero(message = "Discount on item must be a positive number or zero")
	private int discountOnItem;
	private String category;
	private int quantity;
	
}
