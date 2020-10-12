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
	private String title;
	private int score;
	private String detail;
	private String description;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
