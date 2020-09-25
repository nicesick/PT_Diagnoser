package com.example.demo.service;

import com.example.demo.dto.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
	
	public Optional<Member> findOne(String memberId){
		return memberRepository.findById(memberId);
	}

	public Optional<Member> findById(String id){
		return memberRepository.findById(id);
	}

	public Optional<Member> findByENum(String eNum){
		return memberRepository.findByENum(eNum);
	}

	public Optional<Member> findByEmail(String email){
		return memberRepository.findByEmail(email);
	}

	public int registMember(Member member) {
		return memberRepository.save(member);
	}
	
}
