package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		List<Category> categoryList = formService.getCategory();
		
		/*Iterator<String> keys = param.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            String value = (String) param.get(key);
            System.out.println("input : " +key+",  "+value);
        }
        
		List<FormItem> formList = formService.findQuestions(param);
		for(int i= 0 ; i<formList.size(); i++ ) {
			System.out.println(formList.get(i).getCategory() + ", "+ formList.get(i).getContent());
		}
		

		String category = "";
		if("S".equals( param.get("category").toString()) ) {
			category = "(스피치 진단) - 1/4";
		} else if ( "P".equals( param.get("category").toString())) {
			category = "(프레젠테이션 진단) - 2/4";
		} else if ( "E".equals( param.get("category").toString()) ) {
			category = "(발표평가 진단) - 3/4";
		} else if ("U".equals( param.get("category").toString())) {
			category = "(발표불안진단 진단) - 4/4";
		}
		modelAndView.addObject("category" ,param.get("category"));
		modelAndView.addObject("categoryNm", category);
		modelAndView.addObject("formList",formList);
		modelAndView.setViewName("survey");

		return modelAndView;*/
		modelAndView.addObject("categoryList",categoryList);
		modelAndView.setViewName("survey");

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value =  "/surveyList")
	public ModelAndView getSurveyList(ModelAndView modelAndView, @RequestBody Map<String, Object> param) {
		System.out.println("surveyList controller start"); 
		
		Iterator<String> keys = param.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            System.out.println("input : " +key+",  "+ param.get(key));
        }
        
		List<FormItem> formList = formService.findQuestionByCategory(param.get("category_id").toString());
		for(int i = 0 ; i <formList.size(); i ++ ) {
			System.out.println(formList.get(i).getContent());
		}
		
		String category_title = "(" + param.get("category_title").toString() + ")" + " - " + param.get("category_num").toString() + "/" + param.get("category_Totcnt").toString();   
		
		modelAndView.addObject("category_title", category_title);
		modelAndView.addObject("category_id", param.get("category_id"));
		modelAndView.addObject("formList", formList);
		modelAndView.setViewName("survey :: #surveypage");
		
		return modelAndView; 
	}
}
