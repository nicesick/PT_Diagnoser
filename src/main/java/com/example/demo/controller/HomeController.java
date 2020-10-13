package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/*
	 * @RequestMapping("/") public ModelAndView home(ModelAndView modelAndView) {
	 * modelAndView.addObject("content", "content");
	 * modelAndView.setViewName("content");
	 * 
	 * return modelAndView; }
	 */
	@RequestMapping("/content")
	public String content() {
		return "content";
	}
}
