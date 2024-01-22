package com.dealsandcoupons.payment.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.payment.model.Payment;
import com.dealsandcoupons.payment.model.PaymentRecord;
import com.dealsandcoupons.payment.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

	private static final String KEY = "rzp_test_chsz62ATUO203u";
	private static final String KEY_SECRET = "7d8dSRZ1BFsRNYiRBys5jQkQ";
	private static final String CURRENCY = "INR";

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment createTransaction(double amount) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));
			jsonObject.put("currency", CURRENCY);

			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			Order order = razorpayClient.orders.create(jsonObject);
// 			System.out.println(order);
//			return prepareTransactionDetails(order);
			Payment payment = prepareTransactionDetails(order);

// 			Save the payment data to MongoDB
//			paymentRepository.save(payment);

			return payment;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private Payment prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
//String email = order.get("email");
//String productName = order.get("productName");

		Payment payment = new Payment(orderId, currency, amount, KEY);
		return payment;
	}

	public void addPaymentDetails(PaymentRecord paymentRecord) {
		paymentRepository.save(paymentRecord);
	}
}
