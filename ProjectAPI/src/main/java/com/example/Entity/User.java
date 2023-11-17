package com.example.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//table for User data 
@Entity
public class User {
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	@Column(unique = true)
	private String email;//SHould be primary key
	private String gender;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int id, String name, String email, String gender, String Password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.password = Password;
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}
	
	
}
