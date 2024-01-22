package com.dealsandcoupons.login.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dealsandcoupons.login.Repository.UserLoginRepository;
import com.dealsandcoupons.login.model.User;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserLoginRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userInfo= repository.findById(username);
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
