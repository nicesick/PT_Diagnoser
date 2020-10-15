package com.example.demo.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.dto.Category;
import com.example.demo.dto.FormItem;
import com.example.demo.dto.Member;

public interface FormRepository {

	FormItem save(Member member);
	Optional<FormItem> findById(Long id);
	List<FormItem> findByCategory(String category);
	List<FormItem> findAll();
	List<FormItem> findByPage(Map<String, Object> param); 
	List<Category> getCategory();
}
