package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.SessionUtil;

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
}
