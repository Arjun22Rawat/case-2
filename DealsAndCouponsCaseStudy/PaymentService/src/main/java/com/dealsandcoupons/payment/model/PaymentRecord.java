package com.dealsandcoupons.payment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="paymentRecord")
public class PaymentRecord {
	

	@Id
	@NotBlank(message = "Order ID is required")
	private String orderId;
	
	private String email;
	@NotNull(message = "Amount is required")
	@PositiveOrZero(message = "Amount must be a positive or zero integer")
	private Integer amount;
	
	private String productName;
}
