package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Category;
import com.example.demo.dto.FormItem;
import com.example.demo.repository.FormRepository;

@Service
@Transactional
public class FormService {
	private final FormRepository formRepository ;
	
	@Autowired 
	public FormService (FormRepository formRepository) {
		this.formRepository = formRepository; 
	}
	
	public Optional<FormItem> findQuestionById(Long id) {
		return formRepository.findById(id); 
	}
	
	public List<FormItem> findQuestionByCategory(String category){
		List<FormItem> formList = formRepository.findByCategory(category);
		return formList;
	}
	
	public List<FormItem> findQuestions(Map<String, Object> param){
		return formRepository.findByPage(param);
	}
	public List<FormItem> findAllQuestions(){
		return formRepository.findAll();
	}
	public List<Category> getCategory() {
		return formRepository.getCategory();
	}
}
