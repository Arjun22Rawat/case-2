package com.dealsandcoupons.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.gateway.model.Items;
import com.dealsandcoupons.gateway.service.GatewayService;

@RestController
@RequestMapping("/gatewayService")
@CrossOrigin("http://localhost:4200")
public class GatewayController 
{
	@Autowired
	private GatewayService service;

	

	@GetMapping("/getItemFromId/{customerId}")

	public List<Items> getItemFromId(@PathVariable String customerId) {

		return service.getItemFromId(customerId);

	}
	
	@GetMapping("/findCartTotal/{customerId}")

	public double findCartTotal(@PathVariable String customerId) {

		return service.findCartTotal(customerId);
	}
}
