package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Util;
import com.example.demo.dto.Category;
import com.example.demo.dto.FormItem;
import com.example.demo.service.FormService;

@Controller
public class FormController {
	
	private final FormService formService;
	
	@Autowired
	public FormController(FormService formService) {
		this.formService = formService;
	}
	
	@RequestMapping(value =  "/survey")
	public ModelAndView surveyPost( ModelAndView modelAndView , @RequestParam Map<String, Object> param) {

		System.out.println("surveyPost controller start ");
		
		Util.printParam(param);
		
		List<Category> categoryList = formService.getCategory();
		System.out.println(categoryList);
		modelAndView.addObject("categoryList",categoryList);
		
		modelAndView.setViewName("survey");

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value =  "/surveyList")
	public ModelAndView getSurveyList(ModelAndView modelAndView, @RequestBody Map<String, Object> param) {
		System.out.println("surveyList controller start"); 
		
		Util.printParam(param);
		
		String category_title = "(" + param.get("category_title").toString() + ")" + " - " + param.get("category_num").toString() + "/" + param.get("category_Totcnt").toString();   
		List<FormItem> formList = formService.findQuestionByCategory(param.get("category_id").toString());
		
		modelAndView.addObject("category_title", category_title);
		modelAndView.addObject("category_id", param.get("category_id"));
		modelAndView.addObject("formList", formList);
		modelAndView.setViewName("survey :: #surveypage");
		
		return modelAndView; 
	}
}
