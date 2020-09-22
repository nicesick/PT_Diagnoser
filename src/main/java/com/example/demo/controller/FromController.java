package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FromController {

	/*
	 * private final FromService fromService;
	 * 
	 * @Autowired public FromController (FromService fromService) { this.fromService
	 * = fromService; }
	 */
	
	@GetMapping("/")
	public String createForm() {
		
		return "survey";
	}
}
