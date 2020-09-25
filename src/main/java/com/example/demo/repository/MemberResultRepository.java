package com.example.demo.repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;

import java.util.List;
import java.util.Optional;

public interface MemberResultRepository {
	MemberResult save(MemberResult memberResult);
	int saveResult(MemberResult result);

	Optional<MemberResultSum> findById(String id);
	Optional<MemberResultSum> findByENum(String eNum);
	Optional<MemberResultSum> findByEmail(String email);

	List<MemberResultSum> findAll();
}
