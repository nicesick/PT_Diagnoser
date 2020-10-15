package com.example.demo.repository;

import com.example.demo.dto.MemberResult;

import java.util.List;
import java.util.Map;

public interface MemberResultRepository {
	MemberResult save(MemberResult memberResult);
	int saveResult(MemberResult result);
	int saveTotresult(MemberResult result);
	List<Map<String, Object>> findById(String id);
	List<Map<String, Object>> findByENum(String eNum);
	List<Map<String, Object>> findByEmail(String email);
	List<Map<String, Object>> findAll();
}
