package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class JwtRequest {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JwtRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
