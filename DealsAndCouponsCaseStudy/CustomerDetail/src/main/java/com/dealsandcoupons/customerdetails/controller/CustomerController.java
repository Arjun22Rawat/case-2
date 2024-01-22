package com.dealsandcoupons.customerdetails.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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


@CrossOrigin("http://localhost:4200")
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
	public Customer getDetailsById(@PathVariable String id)
	{
		return service.getDetailsById(id).get();
	}
	@GetMapping("/getDetailsByName/{name}")
	public Optional<Customer> getDetailsByName(@PathVariable String name)
	{
		return service.getDetailsByName(name);
	}
	@PutMapping("/updateCustomer/{emailId}/{Name}/{Address}/{contactNumber}/{totalamount}")
	public void updateCustomr(@PathVariable String emailId,@PathVariable String Name,@PathVariable String Address,@PathVariable String contactNumber,@PathVariable double totalamount)
	{
		service.updateCustomer(emailId,Name,Address,contactNumber,totalamount);
	}
	
	@GetMapping("/addCoupon/{emailid}/{couponCode}")

	public void addCoupon(@PathVariable String emailid, @PathVariable String couponCode) {

		service.addCoupon(emailid, couponCode);

	}

	@GetMapping("/getAllCoupons/{emailid}")
	public List<String> getAllCoupons(@PathVariable String emailid){

		return service.getAllCoupons(emailid);

	}
	@GetMapping("/removeCoupon/{emailid}/{couponCode}")
	public void removeCoupon(@PathVariable String emailid,@PathVariable String couponCode)
	{
		service.removeCoupon(emailid,couponCode);
	}
	
	@GetMapping("/updatePrice/{emailid}/{totalAmount}")
	public void updatePrice(@PathVariable String emailid,@PathVariable double totalAmount)
	{
		service.updatePrice(emailid, totalAmount);
	}
	
	
}