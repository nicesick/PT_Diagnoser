package com.example.demo.dto;

/**
 * MemberResultSum
 *
 * user_id - 조회대상 사용자 id
 *
 * speechResult - 스피치 결과
 * presentationResult - 프레젠테이션 결과
 * unrestResult - 불안 결과
 * evaluationResult - PT평가 결과
 */
public class MemberResultSum {
	private String user_id;
	private String workDtim;
	private int speechResult;
	private int presentationResult;
	private int unrestResult;
	private int evaluationResult;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWorkDtim() {
		return workDtim;
	}

	public void setWorkDtim(String workDtim) {
		this.workDtim = workDtim;
	}

	public int getSpeechResult() {
		return speechResult;
	}

	public void setSpeechResult(int speechResult) {
		this.speechResult = speechResult;
	}

	public int getPresentationResult() {
		return presentationResult;
	}

	public void setPresentationResult(int presentationResult) {
		this.presentationResult = presentationResult;
	}

	public int getUnrestResult() {
		return unrestResult;
	}

	public void setUnrestResult(int unrestResult) {
		this.unrestResult = unrestResult;
	}

	public int getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(int evaluationResult) {
		this.evaluationResult = evaluationResult;
	}
}
