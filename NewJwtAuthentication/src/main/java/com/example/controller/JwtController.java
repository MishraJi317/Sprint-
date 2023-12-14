package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.model.User;
import com.example.repo.UserRepo;
import com.example.security.JwtHelper;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class JwtController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User u)
	{
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		uRepo.save(u);
		uRepo.save(u);
		System.out.println(u);
		return "Added Successfully";
	}
	
	
	
	
	
	
	 private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            authenticationManager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }
	

	 @PostMapping("/login")
	 public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
	 {
		 this.doAuthenticate(request.getEmail(), request.getPassword());
		 
		 UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
		 
		 String token = this.jwtHelper.generateToken(userDetails);
	        System.out.println("JWT " + token);

	        //{"token":"value"}

	        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
	 }
	
	
	
	 @ExceptionHandler
	 (BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }

}
