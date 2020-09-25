package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FormItem {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)

	private int id;
	private String category;
	private String content;
	private int score;
	private String useYn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
