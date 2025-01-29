package com.spring.lps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lps.entity.User;
import com.spring.lps.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.save(user);
		
		if(user1 != null)
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		else
			return new ResponseEntity<User>(user1, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/findOne/{name}")
	public ResponseEntity<User> getByUsername(@PathVariable("name") String name){
		System.out.println("controler username :"+name);
		return new ResponseEntity<User>(userService.getUserByUsername(name), HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}
}
