package com.dealsandcoupons.payment.model;

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
@Document(collection="payment")
public class Payment 
{
	@NotBlank(message = "Order ID is required")
private String orderId;
	 @NotBlank(message = "Currency is required")
private String currency;
	 @NotNull(message = "Amount is required")
	    @PositiveOrZero(message = "Amount must be a positive or zero integer")
private Integer amount;
private String key;
//private String email;
//private String productName;
}
