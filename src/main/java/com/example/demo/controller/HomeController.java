package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/*
	 * @RequestMapping("/") public ModelAndView home(ModelAndView modelAndView)
	 * throws Exception { System.out.println("home controller start");
	 * modelAndView.addObject("userNm",
	 * SessionUtil.getAttribute("user_name").toString());
	 * modelAndView.setViewName("main"); return modelAndView; }
	 */

	@RequestMapping("/content")
	public String content() {
		return "content";
	}

	@RequestMapping("/guide")
	public String guide() {
		return "guide";
	}
}
