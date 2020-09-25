package com.example.demo.service;

import com.example.demo.dto.FormItem;
import com.example.demo.dto.MemberResult;
import com.example.demo.dto.MemberResultSum;
import com.example.demo.repository.MemberResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberResultService {
	private final MemberResultRepository memberResultRepository;

	@Autowired
	public MemberResultService(MemberResultRepository memberRepository) {
		this.memberResultRepository = memberRepository;
	}

	public List<MemberResultSum> findMemberResultSumById(String id) {
		return this.memberResultRepository.findById(id);
	}

	public List<MemberResultSum> findMemberResultSumByENum(String eNum) {
		return this.memberResultRepository.findByENum(eNum);
	}

	public List<MemberResultSum> findMemberResultSumByEmail(String email) {
		return this.memberResultRepository.findByEmail(email);
	}

	public List<MemberResultSum> findMemberResultSums() {
		return this.memberResultRepository.findAll();
	}
	public int saveMemberResult(HttpSession session, List<FormItem> formItem) {
		String user_id = (String) session.getAttribute("user_id");

		MemberResult memRlst;
		int result = 0;
 		
		// 's' : speech, 'p' : presentation, 'u' : unrest, 'e' : evaluation
		int speech = 0; 
		int presentation = 0;
		int unrest = 0;
		int evaluation = 0;
			
 		for(int i= 0 ; i <formItem.size(); i++ ) {
 			System.out.println("id : "+formItem.get(i).getId()+"," +formItem.get(i).getScore());
 			
 			if("s".equals(formItem.get(i).getCategory())){
 				speech += formItem.get(i).getScore(); 
			} else if ("p".equals(formItem.get(i).getCategory())) {
				presentation += formItem.get(i).getScore(); 
			} else if ("u".equals(formItem.get(i).getCategory())) {
				unrest += formItem.get(i).getScore();
			} else if ("e".equals(formItem.get(i).getCategory())) {
				evaluation = formItem.get(i).getScore(); 
			}
		}
 		/*테이블 수정 필요 */
 		memRlst = new MemberResult();
 		memRlst.setCategory("s");
 		memRlst.setScore(speech);
 		memRlst.setUser_id(user_id); // 세션 저장하면 세션에서 가져옴
 		result += this.memberResultRepository.saveResult(memRlst);
 		

 		memRlst = new MemberResult();
 		memRlst.setCategory("p");
 		memRlst.setScore(presentation);
 		memRlst.setUser_id(user_id); // 세션 저장하면 세션에서 가져옴
 		result += this.memberResultRepository.saveResult(memRlst);
 		

 		memRlst = new MemberResult();
 		memRlst.setCategory("u");
 		memRlst.setScore(unrest);
 		memRlst.setUser_id(user_id); // 세션 저장하면 세션에서 가져옴
 		result += this.memberResultRepository.saveResult(memRlst);


 		memRlst = new MemberResult();
 		memRlst.setCategory("e");
 		memRlst.setScore(evaluation);
 		memRlst.setUser_id(user_id); // 세션 저장하면 세션에서 가져옴
 		result += this.memberResultRepository.saveResult(memRlst);
 		
		return result; 
	}
}
