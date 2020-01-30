package com.vladyslav.sweater.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String addUser(User user, Model theModel) {
		
		User userFromDb = userRepo.findByUsername(user.getUsername());
		
		if(userFromDb != null) {
			
			String message = "User exsist!";
		
			theModel.addAttribute("message", message);
			
			return "registration";
		}
		
		
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);
		
		return "redirect:/login";
	}

}
