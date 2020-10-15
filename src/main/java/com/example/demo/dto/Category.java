package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Category {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)

	private String id;
	private String title;
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
