package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.Member;

public interface MemberRepository {
	int save(Member member); 
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll(); // 등록회원 전체조회 
}
