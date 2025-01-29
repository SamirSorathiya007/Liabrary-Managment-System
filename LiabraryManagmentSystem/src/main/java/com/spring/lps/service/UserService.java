package com.spring.lps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.lps.entity.User;
import com.spring.lps.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	// create a new user
	public User save(User user) {
		return userRepository.save(user);
	}
	
	//fetch all the user
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	//fetch user by name
	public User getUserByUsername(String name) {
		System.out.println("username :"+name);
		User user = userRepository.findByName(name);
		System.out.println("user :"+user);
		return user;
	}
}
