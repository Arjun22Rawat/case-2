package com.dealsandcoupons.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealsandcoupons.login.Repository.UserLoginRepository;
import com.dealsandcoupons.login.jwt.JwtService;
import com.dealsandcoupons.login.model.User;
import com.dealsandcoupons.login.service.UserLoginService;

import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/loginService")
public class UserLoginController 
{
	@Autowired 
	UserLoginService service;
	
	@Autowired
	private UserLoginRepository userepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody User user)
	{
		try {
			boolean registered = service.registerUser(user);
	        return ResponseEntity.status(registered ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(registered);
	    } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	      }
	}
	@PostMapping("/loginForm")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Boolean> loginUser(@RequestBody User user)
	{
		try {
            boolean loggedIn = service.loginUser(user);
            if (loggedIn) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
	}
	@GetMapping("/getAllUsers")
//	@PreAuthorize("hasAuthority('ADMIN')")
	 public ResponseEntity<List<User>> getAllUsers()
	{
		try {
            List<User> users = service.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	
	@PutMapping("/updateUserById/{id}")
//	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Boolean> updateUserByUsername(@RequestBody User user)
	{
		try {
            boolean updated = service.updateUserByUsername(user);
            if (updated) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
	}
	
	@GetMapping("/getUsersById/{emailId}")

	public User checkUserExistById(@PathVariable String emailId){

		
            return  service.checkUserExistById(emailId);
            
        

	}
	
	@DeleteMapping("/deleteUser/{id}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String id)
	{
		try {
            boolean deleted = service.deleteUser(id);
            if (deleted) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
	}
	
	@PostMapping("/sendVerificationCode")
	public boolean sendVerificationCode(@RequestBody User user) {
		return service.sendVerificationCode(user);
	}
	
	@GetMapping("/verifyAccount/{token}")
	public boolean verifyAccount(@PathVariable("token") String confirmationToken) {
		return service.verifyAccount(confirmationToken);
	}
	@PostMapping("/generatetoken")

	public ResponseEntity<User> authenticateAndGetToken(@RequestBody User user){ 

		//for token creation

	try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));

 

            if (authentication.isAuthenticated()) {
                user.setRoles(userepo.findById(user.getEmailId()).get().getRoles());
                user.setToken(jwtService.generateToken(user.getEmailId()));
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

	}

	@PostMapping("/validateToken")
	public ResponseEntity<String> validateToken(@RequestParam String token) {

		try {
	            jwtService.validateByToken(token);
	            return ResponseEntity.ok("Token is valid");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
	        }



	}
}
