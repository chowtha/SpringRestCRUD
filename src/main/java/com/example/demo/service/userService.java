package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class userService {

	@Autowired
	private UserRepository userrepo;
	
	public String saveUser(User u) {
		userrepo.save(u);
		return "user info saved successfully with user id "+ u.getId(); 
	}
	
	public Optional<User> getUser(int userId) {
		return userrepo.findById(userId);
	}
	
	public List<User> removeUser(int userId){
		userrepo.deleteById(userId);
		return userrepo.findAll();
	}
}
