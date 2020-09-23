package com.example.demo.controller;

import com.example.demo.dto.MemberResultSum;
import com.example.demo.service.MemberResultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ResultController {
	private MemberResultService memberResultService;

	public ResultController(MemberResultService memberResultService) {
		this.memberResultService = memberResultService;
	}

	@RequestMapping("result")
	public ModelAndView home(HttpSession session, ModelAndView modelAndView) {
//		String userId = (String)session.getAttribute("id");
//
//		if (userId == null) {
//			modelAndView.setViewName("login");
//			return modelAndView;
//		}

		String userId = "test01";
		Optional<MemberResultSum> results = memberResultService.findMemberResultSumById(userId);

		modelAndView.addObject("result", results.get());
		modelAndView.setViewName("result");

		return modelAndView;
	}
}
