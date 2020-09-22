package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.FormItem;
import com.example.demo.dto.Member;

public interface FormRepository {
	FormItem save(Member member); 
	Optional<FormItem> findById(Long id);
	Optional<FormItem> findByCategory(String category);
	List<FormItem> findAll(); 	
}
