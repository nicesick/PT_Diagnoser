package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.SessionUtil;
import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Controller
public class LoginController extends HandlerInterceptorAdapter {

	private final MemberService memberService;

	@Autowired
	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 로그인페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() throws Exception {

		if(SessionUtil.getAttribute("user_id") == null) {
			return "login";
		} else {
			return "redirect:result";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGet() throws Exception {

		SessionUtil.removeAttribute("user_id");
		SessionUtil.removeAttribute("user_name");
		SessionUtil.removeAttribute("user_enum");
		SessionUtil.removeAttribute("user_email");
		return "login";
	}

	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void getUsrChk(HttpServletRequest request, @RequestBody Member member) throws Exception {
		Member user = new Member();

		user = memberService.findById(member.getId()).orElse(user);
		System.out.println(user.getPwd() + user.getId());
		System.out.println(member.getPwd() + "," + user.getPwd());

		if (!member.getPwd().equals(user.getPwd())) {
			throw new Exception();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_name", user.getName());
		session.setAttribute("user_enum", user.geteNum());
		session.setAttribute("user_email", user.getEmail());

		System.out.println("login Controller END ");
	}
}
