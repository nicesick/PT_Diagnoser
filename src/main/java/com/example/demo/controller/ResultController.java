package com.example.demo.controller;

import com.example.demo.dto.FormItem;
import com.example.demo.service.MemberResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ResultController {
	private MemberResultService memberResultService;

	@Autowired
	public ResultController(MemberResultService memberResultService) {
		this.memberResultService = memberResultService;
	}

	@RequestMapping("/result")
	public ModelAndView home(HttpSession session, ModelAndView modelAndView) {
		System.out.println("result controller");

		String userId = (String) session.getAttribute("user_id");
		List<Map<String, Object>> results = memberResultService.findMemberResultSumById(userId);

		System.out.println(results);

		modelAndView.addObject("result", results);
		modelAndView.setViewName("result");

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(HttpSession session, Model model, @RequestBody List<FormItem> allData)
	{

		System.out.println("survey/submit controller");
		int rslt = memberResultService.saveMemberResult(session, allData);

		if(rslt >=4) {
			// 성공
		} else {
			// 실패
		}
		return "redirect:/";
	}
}
