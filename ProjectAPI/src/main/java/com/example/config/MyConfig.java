package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
	public AuthenticationSuccessHandler customSuccessHandler;

	//bean of the previous classes to be used in authentication
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	//bean of the password encoder used to convert password to hashcode 
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	//Checkingh whether the user details are matching for the database
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.authenticationProvider(authenticationProvider());
//	}
//	
//	protected void configure(HttpSecurity http) throws Exception
//	{
//	}
	
	//authenticating which user will be able to access the links based on their roles
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		 http.authenticationProvider(authenticationProvider());
		 
         http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN")
                 .requestMatchers("/user/**").hasRole("USER")
                 .requestMatchers("/**").permitAll()
                 .and().formLogin().successHandler(customSuccessHandler)
                 .and().csrf().disable();
         
         
	        return http.build();
	    }
}
