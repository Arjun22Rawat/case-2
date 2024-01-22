package com.dealsandcoupons.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dealsandcoupons.gateway.model.Items;

@Service
public class GatewayService 
{
	@Autowired

	private RestTemplate restTemplate;
	
	public List<Items> getItemFromId(String customerId) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		List<Items> list = new ArrayList<Items>();
		hm = restTemplate.getForObject("http://localhost:8084/cartService/getItemFromCart/" + customerId, HashMap.class);
		for(String itemId : hm.keySet()) {
			Items itemObj = restTemplate.getForObject("http://localhost:8082/itemService/getItemById/" + itemId, Items.class);
			itemObj.setQuantity(hm.get(itemId));
			list.add(itemObj);
			
		}
		return list;
	}
	public double findCartTotal(String customerId) {

		double cartTotal = 0;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm = restTemplate.getForObject("http://localhost:8084/cartService/getItemFromCart/" + customerId, HashMap.class);
		for(String itemId : hm.keySet()) {
			Items itemObj = restTemplate.getForObject("http://localhost:8082/itemService/getItemById/" + itemId, Items.class);
			cartTotal += itemObj.getPrice() * hm.get(itemId);
		}
		return cartTotal;
	}
}


