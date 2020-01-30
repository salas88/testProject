package com.vladyslav.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vladyslav.sweater.dao.MessageRepo;
import com.vladyslav.sweater.entity.Message;


@Controller
public class DemoController {
	
	@Autowired
	private MessageRepo messageRepo;
	
	@GetMapping()
	public String main(Model theModel) {
		
		return "main";
	}
	
	@GetMapping("/main")
	public String list(Model theModel) {
		
		Iterable<Message> messages = messageRepo.findAll();
		
		theModel.addAttribute("messages", messages);
		
		return "index";
	
	}	
	
	@PostMapping("save")
	public String add(@RequestParam String text, @RequestParam String tag, Model theModel ) {
		
		Message message = new Message(text, tag);
		
		messageRepo.save(message);
		
		Iterable<Message> messages = messageRepo.findAll();
		
		theModel.addAttribute("messages", messages);
	
		
		return "index";
	}
	
	@PostMapping("filter")
	public String filter(@RequestParam String filter, Model theModel) {
		
		Iterable<Message> messages;
		
		if(filter.isEmpty()) {
			messages = messageRepo.findAll();
		}else {
			messages = messageRepo.findByTag(filter);
		}
		
		theModel.addAttribute("messages", messages);
		
		return "index";
	}
	
	
	
	

}
