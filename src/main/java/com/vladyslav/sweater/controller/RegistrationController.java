package com.vladyslav.sweater.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vladyslav.sweater.dao.UserRepo;
import com.vladyslav.sweater.entity.Role;
import com.vladyslav.sweater.entity.User;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(User user) {

		User findByDb = userRepo.findByUsername(user.getUsername());
		
		if(findByDb != null) {
			
			return "registration"; 
		}

		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);
		return "redirect:/login";
	}

}
