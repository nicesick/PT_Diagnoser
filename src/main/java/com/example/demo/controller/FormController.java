package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

	/*
	 * private final FromService fromService;
	 * 
	 * @Autowired public FromController (FromService fromService) { this.fromService
	 * = fromService; }
	 */
	
	@PostMapping("form")
	public String createForm() {
		
		return "survey";
	}
}
