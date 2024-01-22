package com.dealsandcoupons.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User 
{
	@Id
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String emailId;
	@NotBlank(message = "Password is required")
	private String password;
	@NotBlank(message = "Roles are required")
	private String roles;
	private String token;
	
}
