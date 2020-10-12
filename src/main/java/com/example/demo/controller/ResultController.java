package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.SessionUtil;
import com.example.demo.service.MemberResultService;

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
	public void submit(@RequestBody Map<String, Object> param)
	{
		System.out.println("survey/submit controller");
		System.out.println(param.get("score"));
		System.out.println(param.get("category"));
		String user_id  = ""; 
		
		//세션값 가져오기 
		try {
			user_id = (String)SessionUtil.getAttribute("user_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		param.put("user_id", user_id);
		
		/*
		 * Iterator<String> keys = param.keySet().iterator(); while( keys.hasNext() ){
		 * String key = keys.next(); String value = (String) param.get(key);
		 * System.out.println("input : " +key+",  "+value); }
		 */
        
		memberResultService.saveMemberResult(param);		
	}


}
