package com.vladyslav.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vladyslav.sweater.dao.MessageRepo;
import com.vladyslav.sweater.entity.Message;
import com.vladyslav.sweater.entity.User;


@Controller
public class DemoController {
	
	@Autowired
	private MessageRepo messageRepo;
	
	@GetMapping()
	public String main(Model theModel) {
		
		return "main";
	}
	
	@GetMapping("/main")
	public String list(@RequestParam(required = false, defaultValue= "" ) String filter, Model theModel) {
		
		Iterable<Message> messages = messageRepo.findAll();
		
		if(filter.isEmpty()) {
			messages = messageRepo.findAll();
		}else {
			messages = messageRepo.findByTag(filter);
		}
		
		theModel.addAttribute("messages", messages);
		theModel.addAttribute("filter", filter);
		
		return "index";
	
	}	
	
	@PostMapping("save")
	public String add(@AuthenticationPrincipal User user, @RequestParam String text, 
					                                      @RequestParam String tag, 
					                                      Model theModel ) {
		
		Message message = new Message(text, tag, user);
		
		messageRepo.save(message);
		
		Iterable<Message> messages = messageRepo.findAll();
		
		theModel.addAttribute("messages", messages);
	
		
		return "index";
	}


}
