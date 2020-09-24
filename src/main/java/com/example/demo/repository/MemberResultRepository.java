package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;

public interface MemberResultRepository {
	MemberResult save(MemberResult memberResult);

	Optional<MemberResultSum> findById(String id);
	List<MemberResultSum> findAll();
	int saveResult(MemberResult result);
}
