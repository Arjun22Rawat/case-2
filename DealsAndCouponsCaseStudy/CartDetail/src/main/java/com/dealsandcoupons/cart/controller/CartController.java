package com.dealsandcoupons.cart.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.cart.model.Cart;
import com.dealsandcoupons.cart.service.CartService;

@RestController
@RequestMapping("/cartService")
@CrossOrigin("http://localhost:4200")
public class CartController 
{
	@Autowired
	private CartService service;
	
	@PostMapping("/addItemInCart")
	public void addItemInCart(@RequestBody Cart cart)
	{
		service.addItemInCart(cart);
	}
	@GetMapping("/addCustomer/{customerId}")
	public void addCustomer(@PathVariable String customerId)
	{
		service.addCustomer(customerId);
	}
	@GetMapping("/getItemFromCart/{customerId}")
	public HashMap<String,Integer> getItemFromCart(@PathVariable String customerId)
	{
		return service.getItemFromCart(customerId);
	}
	
	@GetMapping("/deletetItemFromCart/{customerId}/{itemId}")
	public void deletetItemFromCart(@PathVariable String customerId,@PathVariable String itemId)
	{
		service.deleteItemFromCart(customerId,itemId);
	}
	
	@DeleteMapping("/deleteCartItems/{custId}")
	public boolean deleteUser(@PathVariable String custId)
	{
		return service.deleteUser(custId);
	}
	
}
