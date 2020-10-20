package com.example.demo.service;

import com.example.demo.dto.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public Map<String, Object> modifyMember(Map<String, Object> param) {
/*		Optional<Member> user = memberRepository.findById(SessionUtil.getAttribute("user_id").toString());
		
		 if( user.get().getPwd() == member.getPwd() ) {
			 return memberRepository.modifyUserInfo(member);
		 } else {
			 return 0;
		 }*/

		Map<String, Object> output 			= new HashMap<String, Object>();
		Optional<Member>    origMemInfo 	= memberRepository.findById(param.get("id").toString());

		/*
		 * profile 수정에 대한 예외처리
		 *
		 * 1. 해당 name 이 존재하지 않을 때
		 * 2. 존재하지만 기존 비밀번호가 일치하지 않을 때
		 * 3. 새 비밀번호와 새 비밀번호 확인이 일치하지 않을 때
		 */
		if (!origMemInfo.isPresent()) {
			output.put("msg"		, "FAIL");
			output.put("msgDetail"	, "해당 id가 존재하지 않습니다.");
		} else if (!origMemInfo.get().getPwd().equals(param.get("pwd").toString())) {
			output.put("msg"		, "FAIL");
			output.put("msgDetail"	, "기존 비밀번호가 일치하지 않습니다.");
		} else if (!param.getOrDefault("newPwd1", "").equals(param.get("newPwd2"))) {
			output.put("msg"		, "FAIL");
			output.put("msgDetail"	, "새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
		} else {
			Member member = new Member();

			member.setId(param.get("id").toString());
			member.setEmail(param.get("email").toString());
			member.setPwd(param.get("newPwd1").toString());

			if(memberRepository.modifyUserInfo(member) > 0 ) {
				output.put("msg"		, "SUCCESS");
			} else {
				output.put("msg"		, "FAIL");
				output.put("msgDetail"	, "수정이 정상적으로 처리되지 못했습니다.");
			}
		}

		return output;
	}
}
