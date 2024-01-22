package com.dealsandcoupons.customerdetails.service;

import java.util.ArrayList;
import java.util.List;
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

	public void updateCustomer(String emailId,String Name,String Address,String contactNumber,double totalamount) {
		// TODO Auto-generated method stub
		Optional<Customer> obj = repo.findById(emailId);
		 Customer customerObj=obj.get();
		 customerObj.setName(Name);
		 customerObj.setContactNumber(contactNumber);
		 customerObj.setAddress(Address);
		 customerObj.setTotalAmount(totalamount);
		 repo.save(customerObj);
	}
	
	public void addCoupon(String emailid, String couponCode) {

		List<String> couponList = new ArrayList<String>();

		Customer customerObj = repo.findById(emailid).get(); 

		couponList = customerObj.getCoupons();

		if(couponList == null) {

			List<String> list = new ArrayList<String>();

			list.add(couponCode);
			customerObj.setCoupons(list);
			repo.save(customerObj);
		}
		else {
			for(String coupon : couponList) {
				if(coupon.equals(couponCode)) {
					return;
				}
			}
			couponList.add(couponCode);
			customerObj.setCoupons(couponList);
			repo.save(customerObj);
		}
	}
	public List<String> getAllCoupons(String emailid){

		return repo.findById(emailid).get().getCoupons();

	}

	public void removeCoupon(String emailid,String couponCode) 
	{
		// TODO Auto-generated method stub
		Customer customerObj = repo.findById(emailid).get();
		List<String> list = new ArrayList<>();
		list = customerObj.getCoupons();
		list.remove(couponCode);
		customerObj.setCoupons(list);
		repo.save(customerObj);
		
	}

	public void updatePrice(String emailid, double totalAmount) {
		// TODO Auto-generated method stub
		Customer customerObj = repo.findById(emailid).get();
		customerObj.setTotalAmount(totalAmount);
		repo.save(customerObj);
		
	}
	
	
	
}
