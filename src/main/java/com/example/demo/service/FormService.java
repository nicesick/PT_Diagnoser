package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Optional<FormItem> findQuestionByCategory(String category){
		return formRepository.findByCategory(category);
	}
	
	public List<FormItem> findQuestions(){
		return formRepository.findAll();
	}
}
