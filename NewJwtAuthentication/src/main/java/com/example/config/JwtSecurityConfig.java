package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtAuthenticationEntryPoint;
import com.example.security.JwtAuthenticationFilter;

import jakarta.annotation.security.PermitAll;


@Configuration
public class JwtSecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(c -> c.disable())
		.authorizeHttpRequests(a -> a.requestMatchers("/home/**")
				.authenticated().requestMatchers("/auth/**")
				.permitAll().anyRequest().authenticated())
		.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement(s ->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
	
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	


}
