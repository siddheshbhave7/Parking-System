package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Entities.User;
import java.lang.String;

public interface UserRepository extends JpaRepository<User,Long> {
	
	     User findByEmailAndPassword(String email,String password);
	   //  List<User> findByUserRole(String userrole);
	  List<User>   findByUserRole(String role);
	  
	 // User  deleteUserId(Long userId);
}

