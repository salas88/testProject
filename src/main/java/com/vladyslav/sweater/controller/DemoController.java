package com.vladyslav.sweater.controller;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vladyslav.sweater.dao.UserRepo;
import com.vladyslav.sweater.entity.Message;


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
		
		Iterable<Message> users = userRepo.findAll();
		
		theModel.addAttribute("users", users);
		
		return "index";
	
	}	
	
	@PostMapping("save")
	public String add(@RequestParam String text, @RequestParam String tag, Model theModel ) {
		
		Message message = new Message(text, tag);
		
		userRepo.save(message);
		
		Iterable<Message> messages = userRepo.findAll();
		
		theModel.addAttribute("users", messages);
	
		
		return "index";
	}
	
	@PostMapping("filter")
	public String filter(@RequestParam String filter, Model theModel) {
		
		Iterable<Message> messages;
		
		if(filter.isEmpty()) {
			messages = userRepo.findAll();
		}else {
			messages = userRepo.findByTag(filter);
		}
		
		theModel.addAttribute("messages", messages);
		
		return "index";
	}
	
	
	
	

}
