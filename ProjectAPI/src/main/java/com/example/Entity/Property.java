package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



//table for property data 
@Entity
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Property(int id, String pname, String builder_name, String location, String area, String price) {
		super();
		this.id = id;
		this.pname = pname;
		this.builder_name = builder_name;
		Location = location;
		this.area = area;
		this.price = price;
	}

	public Property(int id) {
		super();
		this.id = id;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String pname;
	private String builder_name;
	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setBuilder_name(String builder_name) {
		this.builder_name = builder_name;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getPname() {
		return pname;
	}

	public String getBuilder_name() {
		return builder_name;
	}

	public String getLocation() {
		return Location;
	}

	public String getArea() {
		return area;
	}

	public String getPrice() {
		return price;
	}

	private String Location;
	
	private String area;
	
	private String price;

}
