package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.Entity.Property;
import com.example.repository.PropertyRepo;

import jakarta.servlet.http.HttpSession;


//controller class for responding to requests for admin related links
@Controller
@RequestMapping("/admin")
//declaring variables for a session to be used in html pages
@SessionAttributes({"pname","location","price"})
public class AdminController{
	
	@Autowired
	private PropertyRepo propertyRepo;
	
	@RequestMapping("/")
	public String getAdmindashboard()
	{
		return "/admin/Dashboard";
	}

	
	@GetMapping("/property")
	public String getPropertyPage()
	{
		return "/admin/PropertyData";
	}
	
	@GetMapping("/home")
	public String refreshpage()
	{
		return "/admin/Dashboard";
	}
	
	//mapping for viewing properties
	@GetMapping("/property/{id}")
	public String getProPage(@PathVariable int id, Model m)
	{
		Optional<Property> optional = propertyRepo.findById(id);	
		Property property = optional.get();
		m.addAttribute("pname", property.getPname());
		m.addAttribute("location", property.getLocation());
		m.addAttribute("price", property.getPrice());
		if(optional.isPresent()) 
		{
			
			return "/admin/propertypage";
		}
		else {
			return "/admin/home";
		}
	}
	
	//mapping for adding properties
	@PostMapping("/addProperty")
	public String addProp(@ModelAttribute Property p)
	{
		propertyRepo.save(p);
		return("/admin/PropertyData");
	}
}
