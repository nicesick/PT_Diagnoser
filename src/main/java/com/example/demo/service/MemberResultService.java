package com.example.demo.service;

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

	public List<MemberResultSum> findMemberResultSums() {
		return this.memberResultRepository.findAll();
	}
}
