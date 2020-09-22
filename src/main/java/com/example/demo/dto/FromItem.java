package com.example.demo.dto;

@javax.persistence.Entity
public class FromItem {
	@org.springframework.data.annotation.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)

	private int id;
	private String category;
	private String content; 
	
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
	public String getContenl() {
		return content;
	}
	public void setContent(String contentl) {
		this.content = contentl;
	}
	
}
