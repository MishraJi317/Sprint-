package com.example.config;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.repo.UserRepo;
import com.example.model.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NewUserDetailsService newUserDetailsService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		final User user = this.userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new com.example.config.NewUserDetailsService(user);
        }
	}

}
