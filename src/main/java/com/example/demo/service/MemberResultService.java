package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MemberResult;
import com.example.demo.repository.MemberResultRepository;

@Service
@Transactional
public class MemberResultService {
	private final MemberResultRepository memberResultRepository;

	@Autowired
	public MemberResultService(MemberResultRepository memberRepository) {
		this.memberResultRepository = memberRepository;
	}

	public List<Map<String, Object>> findMemberResultSumById(String id) {
		return this.memberResultRepository.findById(id);
	}

	public List<Map<String, Object>> findMemberResultSumByENum(String eNum) {
		return this.memberResultRepository.findByENum(eNum);
	}

	public List<Map<String, Object>> findMemberResultSumByEmail(String email) {
		return this.memberResultRepository.findByEmail(email);
	}

	public List<Map<String, Object>> findMemberResultSums() {
		return this.memberResultRepository.findAll();
	}

	public int saveMemberResult(Map<String, Object> param) {
		System.out.println("save Member Result service start");
		MemberResult memRlst;
		int result = 0;
 		
		memRlst = new MemberResult();
 		memRlst.setCategory((String) param.get("category"));
 		memRlst.setUser_id((String) param.get("user_id")); // 세션 저장하면 세션에서 가져옴
 		memRlst.setScore(param.get("score").toString());
 		memRlst.setScore_100(param.get("score_100").toString());
 		memRlst.setWorkDtim(param.get("workDtim").toString());
 		result += this.memberResultRepository.saveResult(memRlst);
 		
 		return result; 
	}
	
	public int saveMemberTotResult(Map<String,Object> param) {
		System.out.println("save Member TOT Result service start");
		
		MemberResult memRlst;
		int result = 0;
 		
		memRlst = new MemberResult(); 
		memRlst.setUser_id((String) param.get("user_id")); // 세션 저장하면 세션에서 가져옴
 		memRlst.setWorkDtim(param.get("workDtim").toString());
 		
 		result += this.memberResultRepository.saveTotresult(memRlst); 
 		
 		return result;  		
	}
}
