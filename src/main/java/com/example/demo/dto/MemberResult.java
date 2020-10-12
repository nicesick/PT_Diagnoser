package com.example.demo.dto;

public class MemberResult {
	private String user_id;
	private String category;
	private String score;
	private String workDtim;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getWorkDtim() {
		return workDtim;
	}

	public void setWorkDtim(String workDtim) {
		this.workDtim = workDtim;
	}
}
