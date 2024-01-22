package com.dealsandcoupons.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.payment.model.Payment;
import com.dealsandcoupons.payment.model.PaymentRecord;
import com.dealsandcoupons.payment.service.PaymentService;

@CrossOrigin("http://localhost:4200/")
@RequestMapping("/paymentService")
@RestController
public class PaymentController 
{
	@Autowired
	private PaymentService service;

	@GetMapping("/createTransaction/{amount}")
	public Payment createTransaction(@PathVariable double amount) 
	{
		return service.createTransaction(amount);
	}
	
	@PostMapping("/addPaymentDetails")
	public void addPaymentDetails(@RequestBody PaymentRecord paymentRecord) {
		service.addPaymentDetails(paymentRecord);
	}
}
