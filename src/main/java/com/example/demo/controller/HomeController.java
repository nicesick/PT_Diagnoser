package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView.setViewName("main");
		modelAndView.addObject("content", "content");

		return modelAndView;
	}
	@RequestMapping("/content")
	public String content() {
		return "content";
	}
}
