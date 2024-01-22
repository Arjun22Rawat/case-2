package com.dealsandcoupons.customerdetails.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection="customerdetails")
public class Customer 
{
	@Id
	private String emailId;
	private String name;
	private int contactNumber;
	private String address;
	private List<String> preferences;
	private double totalAmount; 
}
