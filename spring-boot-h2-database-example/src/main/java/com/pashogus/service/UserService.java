package com.pashogus.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pashogus.model.User;
import com.pashogus.repository.UserRepository;

//@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	 
	     public UserService(UserRepository userRepository)
	     {
	         this.userRepository = userRepository;
	     }
	     
	     

	     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	     {
	         User user = userRepository.findByUsername(username);
	         if(user == null)
	         {
	             throw new UsernameNotFoundException(username);
	         }
	         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	     }



}
