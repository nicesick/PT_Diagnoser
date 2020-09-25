package com.example.demo.repository;

import com.example.demo.dto.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	int save(Member member);

	Optional<Member> findById(String id);
	Optional<Member> findByName(String name);
	Optional<Member> findByENum(String eNum);
	Optional<Member> findByEmail(String email);

	List<Member> findAll(); // 등록회원 전체조회
}
