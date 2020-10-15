package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.SessionUtil;
import com.example.demo.Util;
import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Controller
public class ProfileController {
	private final MemberService memberService;

	@Autowired
	public ProfileController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profileGet(ModelAndView modelAndView){
		
		String userNm = ""; 
		String userEmail = "";

		try {
			userNm = SessionUtil.getAttribute("user_name").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userEmail = SessionUtil.getAttribute("user_email").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelAndView.addObject("userNm", userNm);
		modelAndView.addObject("userEmail", userEmail);
		modelAndView.setViewName("userProfile");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/profile/submit", method = RequestMethod.POST)
	public Map<String, Object> saveUserInfo(@RequestBody Map<String, Object> param) throws Exception {
		System.out.println("profile/submit controller start");
		
		// 파라미터 값 출력 
		Util.printParam(param);
		
		Map<String, Object> output = new HashMap<String, Object>();
		
		Member member = new Member(); 
		member.setName(param.get("name").toString());
		member.setEmail(param.get("email").toString());
		member.setPwd(param.get("pwd").toString());
		
		if(memberService.modifyMember(member) > 0 ) {
			output.put("msg", "SUCCESS"); 
		} else {
			output.put("msg", "FAIL");
		}
		
		return output;
	}

}
