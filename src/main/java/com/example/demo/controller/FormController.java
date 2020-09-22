package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
/*	@Autowired
	private FormRepository formRepository;*/

	/*
	 * private final FromService fromService;
	 * 
	 * @Autowired public FromController (FromService fromService) { this.fromService
	 * = fromService; }
	 */
	
	@PostMapping("main")
	public String createForm() {
/*		List<FormItem> formItemList = formRepository.findAll();

		for (FormItem formItem : formItemList) {
			System.out.println(formItem.getContenl());
			System.out.println(formItem.getCategory());
		}*/

		return "main";
	}
}
