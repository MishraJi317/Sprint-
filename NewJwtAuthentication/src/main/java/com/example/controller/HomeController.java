package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Property;
import com.example.repo.PropertyRepo;

import jakarta.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PropertyRepo pRepo;
	
	
	@GetMapping("/users")
	public String getUsers()
	{
		System.out.println("Get mapping user");
		 return "Users";
	}
	
	@PostMapping("/newProperty")
	public String addProperty(@RequestBody Property p)
	{
		pRepo.save(p);
		return "property added";	
	}
	
	@GetMapping("/getProperties")
	public List<Property> getAllProperties()
	{
		return pRepo.findAll();
	}
	
	@GetMapping("/property/{id}")
	public ResponseEntity<Property> getPropertyById(@PathVariable int id, HttpSession s)
	{
		Optional<Property> optional = pRepo.findById(id);	
		if(optional.isPresent())
		{
			Property property = optional.get();
			return new  ResponseEntity<Property>(optional.get(), HttpStatus.FOUND);
		}
		else {
			return new  ResponseEntity<Property>(HttpStatus.NOT_FOUND);
		}
	}
	

}
