package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.SessionUtil;
import com.example.demo.service.MemberResultService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ResultController {
	private MemberResultService memberResultService;

	@Autowired
	public ResultController(MemberResultService memberResultService) {
		this.memberResultService = memberResultService;
	}
	@RequestMapping("/")
	public ModelAndView getMain(ModelAndView modelAndView) {
		return home(modelAndView);
	}
	
	@RequestMapping("/result")
	public ModelAndView home(ModelAndView modelAndView) {
		System.out.println("result controller");

		String userId= "";
		try {
			userId = SessionUtil.getAttribute("user_id").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, Object>> results = memberResultService.findMemberResultSumById(userId);

		System.out.println(results);

		modelAndView.addObject("result", results);
		modelAndView.setViewName("result");

		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(@RequestBody Map<String, Object> param) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("survey/submit controller");
		System.out.println(param);
		String user_id  = ""; 
		
		//세션값 가져오기 
		try {
			user_id = (String)SessionUtil.getAttribute("user_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = param.get("result").toString();

		List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
		
		Map<String,Object> input = new HashMap<String,Object> (); 
		for (int i = 0; i < list.size(); i++) {
			input.put("user_id", user_id);
			input.put("category", list.get(i).get("id"));
			input.put("score", list.get(i).get("score"));
			memberResultService.saveMemberResult(input);
		}
	}


}
