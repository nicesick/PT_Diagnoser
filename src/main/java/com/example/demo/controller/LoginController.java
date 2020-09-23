package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("main")
	public String homeGet() {
		return homePost();
	}

	@PostMapping("main")
	public String homePost() {
		return "main";
	}
}
