package com.example.demo.controller;

import com.example.demo.SessionUtil;
import com.example.demo.Util;
import com.example.demo.service.MemberResultService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
public class ResultController {
	private MemberResultService memberResultService;

	@Autowired
	public ResultController(MemberResultService memberResultService) {
		this.memberResultService = memberResultService;
	}

	@RequestMapping("/")
	public ModelAndView getMain(ModelAndView modelAndView) {
		System.out.println("getMain controller");

		String userId= "";

		try {
			userId = SessionUtil.getAttribute("user_id").toString();

			List<Map<String, Object>> results = memberResultService.findMemberResultSumById(userId);

			/*
			 * result 결과가 있는지 판별
			 */
			Map<String, Object> result = results.get(0);
			List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("data");
			Map<String, Object> data = datas.get(0);
			String workDtim = (String) data.get("workDtim");

			// System.out.println("result : " + results);
			// System.out.println("workDtim : " + workDtim);

			/*
			 * results 결과가 있다면
			 */
			if (!"".equals(workDtim)) {
				System.out.println("result exist");
				return home(modelAndView);
			} else {
				System.out.println("result not exist");
				return new ModelAndView("redirect:guide");
			}
		} catch (Exception e) {
			modelAndView.setViewName("guide");
			return modelAndView;
		}
	}
	
	@RequestMapping("/result")
	public ModelAndView home(ModelAndView modelAndView) {
		System.out.println("result controller");

		String userId	= "";
		String userName = "";

		try {
			userId 		= SessionUtil.getAttribute("user_id").toString();
			userName 	= SessionUtil.getAttribute("user_name").toString();

			List<Map<String, Object>> results 			= memberResultService.findMemberResultSumById(userId);
			List<Map<String, Object>> allResults 		= memberResultService.findMemberResultSums();
			List<String>              dataCategory 		= Arrays.asList(new String[]{"title","score","detail", "description"});

			String					  filePath			= Util.getFilePath();
			String					  fileBasicNm		= Util.getFileBasic();
			String					  fileProfNm		= Util.getFileProfessional();

			String					  totalResultKey	= "종합점수";

			// System.out.println(results);
			// System.out.println(allResults);
			// System.out.println(dataCategory);

			modelAndView.addObject("userName"			, userName);

			modelAndView.addObject("result"				, results);
			modelAndView.addObject("allResult"			, allResults);
			modelAndView.addObject("dataCategory"		, dataCategory);

			modelAndView.addObject("filePath"			, filePath);
			modelAndView.addObject("fileBasicNm"		, fileBasicNm);
			modelAndView.addObject("fileProfessionalNm"	, fileProfNm);

			modelAndView.addObject("totalResultKey"		, totalResultKey);

			modelAndView.setViewName("result");
		} catch (Exception e) {
			modelAndView.setViewName("guide");
		} finally {
			return modelAndView;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void submit(@RequestBody Map<String, Object> param) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("survey/submit controller");
		System.out.println(param);
		
		
		String workDtim = Util.getToDay("yyyyMMddHHmmss");
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
		input.put("user_id", user_id);
		input.put("workDtim", workDtim);
		
		for (int i = 0; i < list.size(); i++) {
			input.put("category", list.get(i).get("id"));
			input.put("score", list.get(i).get("score"));
			input.put("score_100",list.get(i).get("score_100"));
			memberResultService.saveMemberResult(input);
		}
		
		memberResultService.saveMemberTotResult(input);
	}


}
