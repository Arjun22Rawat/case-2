package com.dealsandcoupons.login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dealsandcoupons.login.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
	@Autowired
	private JwtAuthFilter authFilter;


	@Bean
	public UserDetailsService userDetailsService() {
	return new UserInfoUserDetailsService();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	http.cors()
	.and()
	.csrf().disable()
	.authorizeHttpRequests()
	.requestMatchers("/swagger-ui/**")
	.permitAll()
	.requestMatchers("/loginService/**")
	.permitAll()
	.anyRequest()
	.authenticated()
	.and()
	.formLogin()
	.and()
	.httpBasic()
	.and()
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.authenticationProvider(authenticationProvider())
	.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(userDetailsService());
//	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
	authenticationProvider.setPasswordEncoder(passwordEncoder);
	return authenticationProvider;
	}

	@Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
	    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
	return config.getAuthenticationManager();
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
}