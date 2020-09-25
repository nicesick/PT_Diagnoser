package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Member;
import com.example.demo.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired 
	public MemberService (MemberRepository memberRepository) {
		this.memberRepository = memberRepository; 
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAll(); 
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
	public int registMember(Member member) {
		return memberRepository.save(member);
	}
	
}
