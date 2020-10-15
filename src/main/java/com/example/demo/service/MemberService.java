package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SessionUtil;
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
	public int modifyMember(Member member) throws Exception {
		
		Optional<Member> user = memberRepository.findById(SessionUtil.getAttribute("user_id").toString()); 
		
		 if( user.get().getPwd() == member.getPwd() ) {
			 return memberRepository.modifyUserInfo(member);
		 } else {
			 return 0; 
		 }
		
	}
}
