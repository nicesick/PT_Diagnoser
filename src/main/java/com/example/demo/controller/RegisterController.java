package com.example.demo.controller;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

	// 구현 필요
	private final MemberService memberService;

	@Autowired
	public RegisterController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/register")
	public String homePost(HttpServletRequest request) {
		return "register";
	}
	
	@ResponseBody
	@RequestMapping(value = "/register/submit", method = RequestMethod.POST)
	public void submit(Model model, @RequestBody Member member) {
		System.out.println("register/submit controller start");
		System.out.println(member.toString());
		
		memberService.registMember(member);
	}
}
