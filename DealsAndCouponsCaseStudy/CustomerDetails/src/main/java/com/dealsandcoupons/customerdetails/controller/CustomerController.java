package com.dealsandcoupons.customerdetails.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.customerdetails.model.Customer;
import com.dealsandcoupons.customerdetails.repository.CustomerRepository;
import com.dealsandcoupons.customerdetails.service.CustomerService;
@RestController
@RequestMapping("/customerService")
public class CustomerController 
{
	@Autowired
	private CustomerService service;
	@Autowired
	private CustomerRepository repo;
	
	@PostMapping("/addDetails")
	public boolean addDetails(@RequestBody Customer customer)
	{
		return service.addDetails(customer);
	}
	@GetMapping("/getDetailsById/{id}")
	public Optional<Customer> getDetailsById(@PathVariable String id)
	{
		return service.getDetailsById(id);
	}
	@GetMapping("/getDetailsByName/{name}")
	public Optional<Customer> getDetailsByName(@PathVariable String name)
	{
		return service.getDetailsByName(name);
	}
	@PutMapping("/updateCustomer")
	public void updateCustomr(@RequestBody Customer customer)
	{
		service.updateCustomer(customer);
	}
	
}