package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Controller
public class LoginController extends HandlerInterceptorAdapter {

	// 구현 필요
	private final MemberService memberService;

	@Autowired
	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 로그인페이지
	@GetMapping("login")
	public String loginGet() {
		return "login";
	}

	// 로그인 처리
	@PostMapping("login")
	public void getUsrChk(Model model, @RequestBody Member member) {
		Member user = new Member(); 
		user = memberService.findById(member.getId()).orElse(user);
		System.out.println(user.getPwd() + user.getId());
		System.out.println(member.getPwd() + ","+ user.getPwd());
		
		if(user.getPwd().equals(member.getPwd()) == false) {
			return;
		}
		System.out.println("login Controller END ");
		
	}

}
