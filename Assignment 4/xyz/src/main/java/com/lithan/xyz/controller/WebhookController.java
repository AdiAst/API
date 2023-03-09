package com.lithan.xyz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.xyz.entities.Message;
import com.lithan.xyz.service.WebService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

	@Autowired
	private WebService service;
	
	@PostMapping(value="/message/{sender}")
	public void sendMessage(@PathVariable String sender,@RequestBody Message message) {
		service.sendMessage(sender, message);
		System.out.println(sender+message);
	}
}
