package com.dealsandcoupons.customerdetails.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.customerdetails.model.Customer;
import com.dealsandcoupons.customerdetails.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	private CustomerRepository repo;
	
	
	public boolean addDetails(Customer customer) {
		Optional<Customer> obj = repo.findById(customer.getEmailId()); 
		if(!obj.isEmpty()) {
			return false;
		}
		repo.save(customer);
		return true;
	}
	
	public Optional<Customer> getDetailsById(String id)
	{
		Optional<Customer> cust = repo.findById(id);
		if(cust.isEmpty())
		{
			return null;
		}
		return cust;	
	}
	public Optional<Customer> getDetailsByName(String name)
	{
		Optional<Customer> cust = repo.findByName(name);
		if(cust.isEmpty())
		{
			return null;
		}
		return cust;
		
	}

	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		repo.save(customer);
	}
	
	
}
