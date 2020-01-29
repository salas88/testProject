package com.vladyslav.sweater.controller;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vladyslav.sweater.dao.UserRepo;
import com.vladyslav.sweater.entity.User;


@Controller
public class DemoController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping()
	public String main(Model theModel) {
		
		return "main";
	}
	
	@GetMapping("/main")
	public String list(Model theModel) {
		
		Iterable<User> users = userRepo.findAll();
		
		theModel.addAttribute("users", users);
		
		return "index";
	
	}	
	
	@PostMapping("save")
	public String add(@RequestParam String message, @RequestParam String tag, Model theModel ) {
		
		User user= new User(message, tag);
		
		userRepo.save(user);
		
		Iterable<User> users = userRepo.findAll();
		
		theModel.addAttribute("users", users);
	
		
		return "index";
	}
	
	@PostMapping("filter")
	public String filter(@RequestParam String filter, Model theModel) {
		
		Iterable<User> users;
		
		if(filter.isEmpty()) {
			users = userRepo.findAll();
		}else {
			users = userRepo.findByTag(filter);
		}
		
		theModel.addAttribute("users", users);
		
		return "index";
	}
	
	
	
	

}
