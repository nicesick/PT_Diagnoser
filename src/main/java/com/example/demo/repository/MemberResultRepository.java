package com.example.demo.repository;

import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;

import java.util.List;
import java.util.Optional;

public interface MemberResultRepository {
	MemberResult save(MemberResult memberResult);

	Optional<MemberResultSum> findById(String id);
	List<MemberResultSum> findAll();
}
