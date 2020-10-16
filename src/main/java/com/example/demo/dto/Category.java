package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Category {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)

	private String id;
	private String title;
	private String surveyTyp; 
	
	
	public String getSurveyTyp() {
		return surveyTyp;
	}
	public void setSurveyTyp(String surveyTyp) {
		this.surveyTyp = surveyTyp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
