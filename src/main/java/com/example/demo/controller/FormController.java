package com.example.demo.controller;

import com.example.demo.dto.FormItem;
import com.example.demo.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		

		String category = "";
		if("S".equals( param.get("category").toString()) ) {
			category = "(스피치 진단)";
		} else if ( "P".equals( param.get("category").toString())) {
			category = "(프레젠테이션 진단)";
		} else if ( "E".equals( param.get("category").toString()) ) {
			category = "(발표평가 진단)";
		} else if ("U".equals( param.get("category").toString())) {
			category = "(발표불안진단 진단)";
		}
		modelAndView.addObject("category" ,param.get("category"));
		modelAndView.addObject("categoryNm", category);
		modelAndView.addObject("formList",formList);
		modelAndView.setViewName("survey");

		return modelAndView;
	}

}
