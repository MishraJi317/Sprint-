package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entity.User;


//repository of Customer for accessing JPA Methods througout the program
public interface CustomerRepo extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.email = :email")
	public User getUserByUserName(@Param("email") String email);

}
