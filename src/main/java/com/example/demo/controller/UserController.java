package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.userService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Service", description = "Employee details")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private userService service;

	@PostMapping("/addusers")
	public User createUser( @RequestBody User user) {
		service.saveUser(user);
		return user;
	}


	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@RequestParam(value = "id") int userId) throws ResourceNotFoundException {
		User user = service.getUser(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id :: " + userId+" not found in the repository"));
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable(value = "id") int userId,
			@RequestBody User userDetails) throws ResourceNotFoundException {
		User user = service.getUser(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User with id :: " + userId+" not found in the repository"));
		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok().body(updatedUser);
	}

	@DeleteMapping("/delusers/{id}")
	public List<User> deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
		User user = service.getUser(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id :: " + userId+" not found in the repository"));
		List<User> userList = new ArrayList<>();
		if(user!=null) {
			userList = service.removeUser(userId);
		}
		return userList;
	}
}
