package com.dealsandcoupons.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items 
{
	private String itemId;
	private String itemName;
	private double price;
	private String couponCode;
	private int couponDiscount;
	private byte[] image;
	private int rating;
	private String description;
	private int totalAvailableQuantity;
	private int discountOnItem;
	private String category;
	private int quantity;
}
