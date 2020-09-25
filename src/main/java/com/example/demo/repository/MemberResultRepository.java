package com.example.demo.repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;

import java.util.List;

public interface MemberResultRepository {
	MemberResult save(MemberResult memberResult);
	int saveResult(MemberResult result);

	List<MemberResultSum> findById(String id);
	List<MemberResultSum> findByENum(String eNum);
	List<MemberResultSum> findByEmail(String email);

	List<MemberResultSum> findAll();
}
