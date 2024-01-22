package com.dealsandcoupons.gateway.model;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart 
{
	private String customerId;
	private String itemId;
	private int quantity;
	private HashMap<String,Integer> itemDetails;
	private double cartTotal;
}
