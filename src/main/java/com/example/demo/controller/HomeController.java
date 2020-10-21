package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Util;

@Controller
public class HomeController {

	/*
	 * @RequestMapping("/") public ModelAndView home(ModelAndView modelAndView)
	 * throws Exception { System.out.println("home controller start");
	 * modelAndView.addObject("userNm",
	 * SessionUtil.getAttribute("user_name").toString());
	 * modelAndView.setViewName("main"); return modelAndView; }
	 */

	@RequestMapping("/content")
	public String content() {
		return "content";
	}

	@RequestMapping("/guide")
	public ModelAndView guide(ModelAndView modelAndView) {
		String filePath		= Util.getFilePath();
		String fileBasicNm	= Util.getFileBasic();
		String fileProfNm	= Util.getFileProfessional();

		modelAndView.addObject("filePath"			, filePath);
		modelAndView.addObject("fileBasicNm"		, fileBasicNm);
		modelAndView.addObject("fileProfessionalNm"	, fileProfNm);

		modelAndView.setViewName("guide");

		return modelAndView;
	}
}
