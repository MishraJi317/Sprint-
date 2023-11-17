package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Property;


//repository of Property for accessing JPA Methods througout the program
public interface PropertyRepo extends JpaRepository<Property, Integer>{

}
