package com.dealsandcoupons.login.model;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document()
public class ConfirmationToken 
{
	@Id
	private String confirmationToken;
	private User user;
	
	public ConfirmationToken(User user) {
		this.user = user;
//		confirmationToken = UUID.randomUUID().toString();
		Random random = new Random();
		confirmationToken = String.format("%04d", random.nextInt(10000));
	}
}
