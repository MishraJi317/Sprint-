package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Entity.User;
import com.example.repository.CustomerRepo;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepo;
	
	//method for fetching a user from the database
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = customerRepo.getUserByUserName(username);
		
		//checking if a user actually exists or not
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user ");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		
		return customUserDetails;
	}

}
