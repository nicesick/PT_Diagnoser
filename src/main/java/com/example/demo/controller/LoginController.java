package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MemberService;

@Controller
public class LoginController {
	
	// 구현 필요
	private final MemberService memberService;
	
	@Autowired
	public LoginController (MemberService memberService){
		this.memberService = memberService; 
	}
	
	
	@RequestMapping("login")
	public String homePost(HttpServletRequest request ) {
		return "login";
	}
	
}
