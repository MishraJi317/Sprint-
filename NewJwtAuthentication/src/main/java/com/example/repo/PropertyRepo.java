package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Property;

public interface PropertyRepo extends JpaRepository<Property, Integer>{

}
