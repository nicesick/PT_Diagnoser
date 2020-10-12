package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value =  "/survey")
	public ModelAndView surveyPost( ModelAndView modelAndView , @RequestParam Map<String, Object> param) {
		
		System.out.println("surveyPost controller start ");
		modelAndView.addObject("category" , param.get("category"));
		
		Iterator<String> keys = param.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            String value = (String) param.get(key);
            System.out.println("input : " +key+",  "+value);
        }
        
		List<FormItem> formList = formService.findQuestions(param);
		for(int i= 0 ; i<formList.size(); i++ ) {
			System.out.println(formList.get(i).getCategory() + ", "+ formList.get(i).getContent());
		}
		
		modelAndView.setViewName("survey");
		modelAndView.addObject("formList",formList);
		
		return modelAndView;
	}

}
