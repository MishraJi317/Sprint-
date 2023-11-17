package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Sender;
import com.example.Entity.User;

import com.example.Entity.Property;
import com.example.repository.CustomerRepo;
import com.example.repository.PropertyRepo;

import jakarta.servlet.http.HttpSession;


//controller class for responding to requests for handling unregisterd or users that have not logged in yet 
@Controller
public class RequestController {
	//declaring variables of all the classes created
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private PropertyRepo propertyRepo;
	@Autowired
	private Sender sender;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@GetMapping("/home")
	public String getHomePage()
	{
		return "home";
	}
	
	//mapping for registering a new user
	@GetMapping("/signup")
	public String getSignUpPage()
	{
		return "CustomerData";
	}
	
	//mapping for SignIn
	@GetMapping("/login")
	public String getloginPage()
	{
		return "LoginPage";
	}
	
	

	
//	@GetMapping("/property/{id}")
//	public ResponseEntity<Property> getPropertyById(@PathVariable int id, HttpSession s)
//	{
//		Optional<Property> optional = propertyRepo.findById(id);	
//		if(optional.isPresent())
//		{
//			Property property = optional.get();
//			s.setAttribute("PName", property.getPname());
//			s.setAttribute("Location", property.getLocation());
//			s.setAttribute("Price", property.getPrice());
//			System.out.println(s.getAttribute("PName"));
//			System.out.println(s.getAttribute("Location"));
//			System.out.println(s.getAttribute("Price"));
//			return new  ResponseEntity<Property>(optional.get(), HttpStatus.FOUND);
//		}
//		else {
//			return new  ResponseEntity<Property>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	
	//saving new user data to database with error handling 
	@PostMapping("/addCustomer")
	public String addCust(@ModelAttribute User c ,HttpSession session)
	{
		try {
			c.setPassword(passwordEncoder.encode(c.getPassword()));
			customerRepo.save(c);
			System.out.println(c.getEmail());
			String gender = c.getGender();
			String suffix ="";
			if (gender.equalsIgnoreCase("male")){
				suffix = "Mr";
			}
			else {
				suffix = "Miss";
			}
			System.out.println(	session.getAttribute("msg"));
		
			String body ="Hello "+suffix+" "+c.getName()+", Thankyou for Joining amis consultancy . We will get in touch with you soon";
			
			sender.sendMail(c.getEmail(), "Welcome", body);
			return("CustomerData");
		} catch (Exception e) {
			// TODO: handle exception
			return "err";
		}
//		session.setAttribute("msg", "true");
//		c.setPassword(passwordEncoder.encode(c.getPassword()));
//		customerRepo.save(c);
//		System.out.println(c.getEmail());
//		String gender = c.getGender();
//		String suffix ="";
//		if (gender.equalsIgnoreCase("male")){
//			suffix = "Mr";
//		}
//		else {
//			suffix = "Miss";
//		}
//		System.out.println(	session.getAttribute("msg"));
//	
//		String body ="Hello "+suffix+" "+c.getName()+", Thankyou for Joining amis consultancy . We will get in touch with you soon";
//		
//		sender.sendMail(c.getEmail(), "Welcome", body);
		
	}
	
	
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<User> getCustomers(@PathVariable int id)
	{
		Optional<User> dpt = customerRepo.findById(id);
		
		if(dpt.isPresent())
		{
			return new  ResponseEntity<User>(dpt.get(), HttpStatus.FOUND);
		}
		else {
			return new  ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/Customers")
	@ResponseBody
	public List<User> getAllCustomers()
	{
		return customerRepo.findAll();
	}
	
	
	
//	@GetMapping("/getProperty/{id}")
//	public ResponseEntity<Property> getProperties(@PathVariable int id)
//	{
//		Optional<Property> dpt = propertyRepo.findById(id);
//		
//		if(dpt.isPresent())
//		{
//			return new  ResponseEntity<Property>(dpt.get(), HttpStatus.FOUND);
//		}
//		else {
//			return new  ResponseEntity<Property>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/Properties")
	@ResponseBody
	public List<Property> getAllProperties()
	{
		return propertyRepo.findAll();
	}
	
	
	@PutMapping("/update/{id}")
	public String updateDepartment(@PathVariable int id)
	{
		Optional<User> dpt = customerRepo.findById(id);
		
		User d = dpt.get();
		d.setName("Abhishek");	
		customerRepo.save(d);
		return "Updated Record";
	}
	
	

}
