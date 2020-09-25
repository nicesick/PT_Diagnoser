package com.example.demo.service;

import com.example.demo.dto.FormItem;
import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;
import com.example.demo.repository.MemberResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberResultService {
	private final MemberResultRepository memberResultRepository;

	@Autowired
	public MemberResultService(MemberResultRepository memberRepository) {
		this.memberResultRepository = memberRepository;
	}

	public Optional<MemberResultSum> findMemberResultSumById(String id) {
		return this.memberResultRepository.findById(id);
	}

	public Optional<MemberResultSum> findMemberResultSumByENum(String eNum) {
		return this.memberResultRepository.findByENum(eNum);
	}

	public Optional<MemberResultSum> findMemberResultSumByEmail(String email) {
		return this.memberResultRepository.findByEmail(email);
	}

	public List<MemberResultSum> findMemberResultSums() {
		return this.memberResultRepository.findAll();
	}
	public int saveMemberResult(List<FormItem> formItem) {
		MemberResult memRlst;
		int result = 0; 
 		
		// 's' : speech, 'p' : presentation, 'u' : unrest, 'e' : evaluation
		int speech = 0; 
		int presentation = 0;
		int unrest = 0;
		int evaluation = 0;

 		for(int i= 0 ; i <formItem.size(); i++ ) {
			
			
			if(formItem.get(i).getCategory() == "s") {
				speech += formItem.get(i).getScore(); 
			} else if (formItem.get(i).getCategory() == "p" ) {
				presentation += formItem.get(i).getScore(); 
			} else if (formItem.get(i).getCategory() == "u") {
				unrest += formItem.get(i).getScore();
			} else if (formItem.get(i).getCategory() == "e") {
				evaluation = formItem.get(i).getScore(); 
			}
		}
 		/*테이블 수정 필요 */
 		memRlst = new MemberResult();
 		memRlst.setCategory("s");
 		memRlst.setScore(speech);
 		memRlst.setUser_id("park@lotte.net"); // 세션 저장하면 세션에서 가져옴 
 		result += this.memberResultRepository.saveResult(memRlst);
 		
 		memRlst.setCategory("p");
 		memRlst.setScore(presentation);
 		memRlst.setUser_id("park@lotte.net"); // 세션 저장하면 세션에서 가져옴 
 		result += this.memberResultRepository.saveResult(memRlst);
 		
 		memRlst.setCategory("u");
 		memRlst.setScore(unrest);
 		memRlst.setUser_id("park@lotte.net"); // 세션 저장하면 세션에서 가져옴 
 		result += this.memberResultRepository.saveResult(memRlst);

 		memRlst.setCategory("e");
 		memRlst.setScore(evaluation);
 		memRlst.setUser_id("park@lotte.net"); // 세션 저장하면 세션에서 가져옴 
 		result += this.memberResultRepository.saveResult(memRlst);
 		
		return result; 
	}
}
