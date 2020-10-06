package com.example.demo.controller;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends HandlerInterceptorAdapter {

	// 구현 필요
	private final MemberService memberService;

	@Autowired
	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 로그인페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		return "login";
	}

	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void getUsrChk(HttpServletRequest request ,Model model, @RequestBody Member member) {
		Member user = new Member(); 
		user = memberService.findById(member.getId()).orElse(user);
		System.out.println(user.getPwd() + user.getId());
		System.out.println(member.getPwd() + ","+ user.getPwd());
		
		if(user.getPwd().equals(member.getPwd()) == false) {
			return;
		}
		
		HttpSession session = request.getSession(); 
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_name", user.getName());
		session.setAttribute("user_enum", user.geteNum());
		session.setAttribute("user_email", user.getEmail());
		
		System.out.println("login Controller END ");
		
	}

}
