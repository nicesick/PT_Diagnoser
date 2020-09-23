package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.FormItem;
import com.example.demo.service.FormService;

@Controller
public class FormController {
	
	private final FormService formService;
	
	@Autowired
	public FormController(FormService formService) {
		this.formService = formService;
	}
	
	@RequestMapping("survey")
	public ModelAndView surveyPost() {

		ModelAndView mv = new ModelAndView(); 
		List<FormItem> formList = formService.findQuestions();
		
		for(int i =0 ;i<formList.size(); i++ ) {
			System.out.println(formList.get(i).getId() + ", " + formList.get(i).getCategory() + ", "+ formList.get(i).getContent());
		}
		mv.addObject("formList", formList);
		mv.setViewName("survey");
		return mv;
	}
}
